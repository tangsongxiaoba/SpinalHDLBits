package SpinalHDLBits

package SpinalHDLBits.Alwaysblock2

import spinal.core._
import SpinalHDLBits.Config._

case class Alwaysblock2() extends Component {
    val io = new Bundle {
        val a               = in Bool ()
        val b               = in Bool ()
        val out_assign      = out Bool ()
        val out_always_comb = out Bool ()
        val out_always_ff   = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    io.out_always_ff.setAsReg()
    io.out_assign      := io.a ^ io.b
    io.out_always_comb := io.a ^ io.b
    io.out_always_ff   := io.a ^ io.b

}

object Alwaysblock2Verilog extends App {
    Config.spinal.generate(Alwaysblock2()).printPruned()
}
