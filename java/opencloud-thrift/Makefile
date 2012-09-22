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

# Default target is everything
target: all

# Tools
#THRIFT = ../../compiler/cpp/thrift
#THRIFT = /usr/local/bin/thrift
THRIFT = thrift

all: normal inline link

normal: stubs

inline: stubs-inline

link:
	rsync -avz gen-php/* ../../php/opencloud-php-client/lib/php/src/packages/
	rsync -avz gen-java/* src/main/java/
	
stubs: opencloud.thrift
	$(THRIFT) --gen php:server opencloud.thrift
	$(THRIFT) --gen java opencloud.thrift

stubs-inline: opencloud.thrift
	$(THRIFT) --gen php:inlined opencloud.thrift

clean:
	$(RM) -r gen-php gen-phpi gen-java
