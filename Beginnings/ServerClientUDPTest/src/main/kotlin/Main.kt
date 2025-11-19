import java.net.DatagramSocket
import java.net.DatagramPacket

fun main() {
    val socket = DatagramSocket(4445)

    while (true) {
        val buf = ByteArray(1024)
        var packet = DatagramPacket(buf, buf.size)
        socket.receive(packet)
        var recv = String(buf, Charsets.UTF_8)
        recv = recv.dropLastWhile { it.code <= 20 }
        println(recv)
        if (recv == "quit") {
            val echobuf = Charsets.UTF_8.encode("Goodbye!\n").array()
            packet = DatagramPacket(echobuf, echobuf.size, packet.address, packet.port)
            socket.send(packet)
            break;
        } else {
            val echobuf = Charsets.UTF_8.encode(recv + "\n").array()
            packet = DatagramPacket(echobuf, echobuf.size, packet.address, packet.port)
            socket.send(packet)
        }
    }

    socket.close()
}