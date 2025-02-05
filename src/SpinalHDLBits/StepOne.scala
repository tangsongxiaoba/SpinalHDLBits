package SpinalHDLBits

package SpinalHDLBits.StepOne

import spinal.core._
import SpinalHDLBits.Config._

case class StepOne() extends Component {
    val io = new Bundle {
        val one = out port Bool()
    }

    setDefinitionName("top_module")
    io.one.setName("one")

    io.one := True
}

object StepOneVerilog extends App {
    Config.spinal.generate(StepOne())
}
