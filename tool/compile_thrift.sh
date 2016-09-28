#!/bin/sh

DIR=`cd \`dirname ${BASH_SOURCE[0]}\`/.. && pwd`

rm -fr ${DIR}/blog-api-common/src/main/java/com/fenbi/blog/api/thrift/
thrift --gen java:beans -out ${DIR}/blog-api-common/src/main/java blog-api-common/src/main/thrift/BlogApi.thrift
