#!/usr/bin/python

import zookeeper, time, threading
from const import Constant

connected = False
conn_cv = threading.Condition()

def my_connection_watcher(handle, type, state, path):
    global connected, conn_cv
    print "Connected, handle is", handle
    conn_cv.acquire()
    connected = True
    conn_cv.notifyAll()
    conn_cv.release()

def my_getc_watch(handle, type, state, path ):
    print "Watch fired -- "
    print type, state, path

class ZooKeeperAdapter:

    @staticmethod
    def getAddress():
        conn_cv.acquire()
        print "Connecting to", Constant.SERVER_PORT, "-- "
        handle = zookeeper.init(Constant.SERVER_PORT, my_connection_watcher)
        while not connected:
            conn_cv.wait()
        conn_cv.release()

        try:
            list = zookeeper.get_children(handle, Constant.ZK_DIR, my_getc_watch)
            if list is not None:
                data = zookeeper.get(handle, Constant.ZK_DIR + "/" + list[0], None)
                if data[0] == "enabled":
                    print list[0]
            return list[0].split(":")
        except:
            pass

if __name__ == '__main__':
    print ZooKeeperAdapter.getAddress()
