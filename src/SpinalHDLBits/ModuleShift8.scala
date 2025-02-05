package SpinalHDLBits

package SpinalHDLBits.ModuleShift8

import spinal.core._
import SpinalHDLBits.Config._

case class ModuleShift8() extends Component {
    val io = new Bundle {
        val clk = in Bool ()
        val d   = in Bits (8 bits)
        val sel = in UInt (2 bits)
        val q   = out Bits (8 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val myDff8 = Array.tabulate(3)(_ => new MyDff8)

    myDff8.indices.foreach(i => io.clk <> myDff8(i).io.clk)
    (0 to 1).foreach(i => myDff8(i).io.q <> myDff8(i + 1).io.d)

    io.d <> myDff8(0).io.d
    io.q := io.sel.mux(
      (0 -> io.d) +:
          (1 to myDff8.length).map(i => i -> myDff8(i - 1).io.q): _*
    )
}

class MyDff8 extends BlackBox {
    val io = new Bundle {
        val clk = in Bool ()
        val d   = in Bits (8 bits)
        val q   = out Bits (8 bits)
    }
    noIoPrefix()
    setBlackBoxName("my_dff8")
}

object ModuleShift8Verilog extends App {
    Config.spinal.generate(ModuleShift8())
}
