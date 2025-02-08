package SpinalHDLBits

package SpinalHDLBits.Countslow

import spinal.core._
import SpinalHDLBits.Config._

case class Countslow() extends Component {
    val io = new Bundle {
        val slowena = in Bool ()
        val q       = out UInt (4 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val reg = clockDomain.withSyncReset on RegInit(U"0000")
    when(io.slowena) {
        when(io.q === 9) {
            reg := 0
        } otherwise {
            reg := io.q + 1
        }
    }
    io.q := reg

}

object CountslowVerilog extends App {
    Config.spinal.generate(Countslow()).printPruned()
}
