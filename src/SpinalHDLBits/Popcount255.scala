package SpinalHDLBits

package SpinalHDLBits.Popcount255

import spinal.core._
import SpinalHDLBits.Config._
import spinal.lib.CountOne

case class Popcount255() extends Component {
    val io = new Bundle {
        val in_ = in UInt(255 bits)
        val out_ = out port Reg(UInt(8 bits))
    }
    
    setDefinitionName("top_module")
    noIoPrefix()

    io.out_.setAsComb()

    io.out_ := CountOne(io.in_)
}

object Popcount255Verilog extends App {
    Config.spinal.generate(Popcount255()).printPruned()
}
