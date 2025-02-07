package SpinalHDLBits

package SpinalHDLBits.DffsAndGates

import spinal.core._
import SpinalHDLBits.Config._

case class DffsAndGates() extends Component {
    val io = new Bundle {
        val clk = in Bool ()
        val x   = in Bool ()
        val z   = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val myClockDomain = ClockDomain(
      clock = io.clk
    )

    val myClockArea = new ClockingArea(myClockDomain) {
        val xorVal, andVal, orVal, reg1, reg2, reg3 = Bool()
        reg1   := RegNext(xorVal)
        reg2   := RegNext(andVal)
        reg3   := RegNext(orVal)
        xorVal := reg1 ^ io.x
        andVal := ~reg2 & io.x
        orVal  := ~reg3 | io.x
        io.z   := ~(reg1 | reg2 | reg3)
    }
}

object DffsAndGatesVerilog extends App {
    Config.spinal.generate(DffsAndGates()).printPruned()
}
