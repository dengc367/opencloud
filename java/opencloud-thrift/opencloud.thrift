namespace java com.renren.api.thrift

struct ConnectionInfo{
    1: string username;
    2: string password;
    3: string address; 
    4: string port;
    5: string type
}

exception OpenCloudException{
    1: i32 type,
    2: string msg
}

service ConnectionService{
    list<ConnectionInfo> getConnection(1: string key, 2: string type) throws (1: OpenCloudException e)
}

