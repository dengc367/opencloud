#!/usr/bin/env python

#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements. See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied. See the License for the
# specific language governing permissions and limitations
# under the License.
#

import sys
sys.path.append('./lib')

from opencloud import ConnectionService
from opencloud.ttypes import *

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.protocol import TCompactProtocol
from zkclient import ZooKeeperAdapter

from const import Constant
try:

  server, port = ZooKeeperAdapter.getAddress()
  # Make socket
  transport = TSocket.TSocket(server, port)

  # Buffering is critical. Raw sockets are very slow
  transport = TTransport.TBufferedTransport(transport)

  # Wrap in a protocol
  #protocol = TBinaryProtocol.TBinaryProtocol(transport)
  protocol = TCompactProtocol.TCompactProtocol(transport)

  # Create a client to use the protocol encoder
  client = ConnectionService.Client(protocol)

  # Connect!
  transport.open()

  connInfo = client.getConnection(Constant.APP_KEY, Constant.MEMCACHE)
  print connInfo[0].address
  print connInfo[0].port

  # Close!
  transport.close()

except Thrift.TException, tx:
  print '%s' % (tx.message)
