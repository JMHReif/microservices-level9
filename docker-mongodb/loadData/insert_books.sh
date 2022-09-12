#! /bin/bash
auth="--authenticationDatabase admin --username mongoadmin --password Testing123"
db="goodreads"
options="--mode=insert"

mongoimport $auth --db=$db --collection=book --file=goodreads_books_10k.json