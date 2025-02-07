package SpinalHDLBits

package SpinalHDLBits.Count10

import spinal.core._
import SpinalHDLBits.Config._

case class Count10() extends Component {
    val io = new Bundle {
        val q = out UInt (4 bits)
    }

    val reg = clockDomain.withSyncReset on RegInit(U"0000")

    when(io.q === 9) {
        reg := 0
    } otherwise {
        reg := io.q + 1
    }

    io.q := reg

    setDefinitionName("top_module")
    noIoPrefix()
}

object Count10Verilog extends App {
    Config.spinal.generate(Count10()).printPruned()
}
