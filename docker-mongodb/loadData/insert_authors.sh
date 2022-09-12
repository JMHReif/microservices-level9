#! /bin/bash
auth="--authenticationDatabase admin --username mongoadmin --password Testing123"
db="goodreads"
options="--mode=insert"

mongoimport $auth --db=$db --collection=author --file=goodreads_bookAuthors_trim.json