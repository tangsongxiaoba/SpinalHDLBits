package SpinalHDLBits

package SpinalHDLBits.Module

import spinal.core._
import SpinalHDLBits.Config._

case class Module() extends Component {
    val io = new Bundle {
        val a    = in Bool ()
        val b    = in Bool ()
        val out_ = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val modA = new ModA()

    io.a <> modA.io.in1
    io.b <> modA.io.in2
    io.out_ <> modA.io.out_

}

class ModA extends BlackBox {
    val io = new Bundle {
        val in1, in2 = in Bool ()
        val out_     = out Bool ()
    }
    noIoPrefix()
    setBlackBoxName("mod_a")
}

object ModuleVerilog extends App {
    Config.spinal.generate(Module())
}
