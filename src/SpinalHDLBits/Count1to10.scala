package SpinalHDLBits

package SpinalHDLBits.Count1to10

import spinal.core._
import SpinalHDLBits.Config._

case class Count1to10() extends Component {
    val io = new Bundle {
        val q = out UInt (4 bits)
    }

    val reg = clockDomain.withSyncReset on RegInit(U"0001")

    when(io.q === 10) {
        reg := 1
    } otherwise {
        reg := io.q + 1
    }

    io.q := reg

    setDefinitionName("top_module")
    noIoPrefix()
}

object Count1to10Verilog extends App {
    Config.spinal.generate(Count1to10()).printPruned()
}
