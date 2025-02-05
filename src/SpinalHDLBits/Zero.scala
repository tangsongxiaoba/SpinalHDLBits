package SpinalHDLBits

package SpinalHDLBits.Zero

import spinal.core._
import SpinalHDLBits.Config._

case class Zero() extends Component {
    val io = new Bundle {
        val zero = out port Bool()
    }

    setDefinitionName("top_module")
    io.zero.setName("zero")

    io.zero := False
}

object ZeroVerilog extends App {
    Config.spinal.generate(Zero())
}
