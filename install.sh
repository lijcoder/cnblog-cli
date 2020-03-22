#!/bin/bash

if [ -z "$1" ]; then
    echo "error: install directory not null"
    exit 1
fi
ROOT_PATH=$1
LIB_PATH=${ROOT_PATH}/lib

mkdir -p ${ROOT_PATH}
mkdir -p ${LIB_PATH}

cp cnblog-cli.sh ${ROOT_PATH}
cp cnblog-cli.config ${ROOT_PATH}
cp target/*jar-with-dependencies.jar  ${LIB_PATH}/cnblog-cli.jar

sed -i "s!\$install_cnblog_config!${ROOT_PATH}/cnblog-cli.config!g"  ${ROOT_PATH}/cnblog-cli.sh
sed -i "s!\$install_jar!${LIB_PATH}/cnblog-cli.jar!g"  ${ROOT_PATH}/cnblog-cli.sh
