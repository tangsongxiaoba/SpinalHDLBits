package SpinalHDLBits

package SpinalHDLBits.ModuleShift

import spinal.core._
import SpinalHDLBits.Config._

case class ModuleShift() extends Component {
    val io = new Bundle {
        val clk = in Bool ()
        val d   = in Bool ()
        val q   = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val myDff = Array.tabulate(3)(_ => new MyDff)

    io.clk <> myDff(0).io.clk
    io.clk <> myDff(1).io.clk
    io.clk <> myDff(2).io.clk
    io.d <> myDff(0).io.d
    myDff(0).io.q <> myDff(1).io.d
    myDff(1).io.q <> myDff(2).io.d
    myDff(2).io.q <> io.q
}

class MyDff extends BlackBox {
    val io = new Bundle {
        val clk, d = in Bool ()
        val q      = out Bool ()
    }
    noIoPrefix()
    setBlackBoxName("my_dff")
}

object ModuleShiftVerilog extends App {
    Config.spinal.generate(ModuleShift())
}
