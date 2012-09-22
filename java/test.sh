#!/bin/sh

ssh ugc.d.xiaonei.com test -d /data/dzc2
echo $?

#ssh ugc.d.xiaonei.com echo 'aba' > /data/dzc/test.sh

if [ ! `ssh ugc.d.xiaonei.com test -d /data/dzc; echo $?` -eq 0 ]; then
    echo '1'
else 
    echo '2'
fi

echo <<EOF >>log1
here you
are
EOF

cat <<EOF >>log2
hew yos
sa
EOF

echo "
	#!
	dsafas
" >> log3
