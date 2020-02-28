#!/bin/bash

wget https://datasets.imdbws.com/title.basics.tsv.gz -O /tmp/title.basics.tsv.gz
gzip -d /tmp/title.basics.tsv.gz
