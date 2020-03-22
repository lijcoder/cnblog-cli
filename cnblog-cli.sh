#!/bin/bash

cmd=$1
file=$(cd `dirname $2`; pwd)/$(basename $2)
cnblog_config=$install_cnblog_config
jar_path=$install_jar

java -Dconfig.file=${cnblog_config} -Dexec=${cmd} -Dblog=${file} -jar  ${jar_path}
