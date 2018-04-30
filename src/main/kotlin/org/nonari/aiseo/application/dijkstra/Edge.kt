package org.nonari.aiseo.application.dijkstra

class Edge(val src: Int, val dst: Int, val len: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this.javaClass != other?.javaClass) return false

        other as Edge

        if (src != other.src) {
            return (src == other.dst) && (dst == other.src)
        }
        if (dst != other.dst) return false
        if (len != other.len) return false

        return true
    }

    override fun hashCode(): Int {
        var result = (src + dst) * Math.abs(src - dst) * 31
        result = 31 * result + len
        return result
    }

    override fun toString(): String {
        return "{$src <-($len)-> $dst}"
    }
}
