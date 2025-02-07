package SpinalHDLBits

package SpinalHDLBits.Adder100i

import spinal.core._
import SpinalHDLBits.Config._
import spinal.core.internals.Operator.BitVector.Add

case class Adder100i() extends Component {
    val io = new Bundle {
        val a, b = in Bits (100 bits)
        val cin  = in Bool ()
        val cout = out Bits (100 bits)
        val sum  = out Bits (100 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val add1Array = Array.tabulate(100)(_ => new Add1())
    val carryIn   = Bits(101 bits).noCombLoopCheck()
    carryIn(0) := io.cin

    add1Array.indices.foreach(i =>
        (
          add1Array(i).io.a <> io.a(i),
          add1Array(i).io.b <> io.b(i),
          add1Array(i).io.cin <> carryIn(i),
          add1Array(i).io.cout <> carryIn(i + 1),
          add1Array(i).io.sum <> io.sum(i)
        )
    )

    io.cout := carryIn(100 downto 1)

}

case class Add1() extends Component {
    val io = new Bundle {
        val a, b, cin = in Bool ()
        val sum, cout = out Bool ()
    }

    setDefinitionName("add1")
    noIoPrefix()

    io.sum  := io.a ^ io.b ^ io.cin
    io.cout := io.a & io.b | io.a & io.cin | io.b & io.cin
}

object Adder100iVerilog extends App {
    Config.spinal.generate(Adder100i()).printPruned()
}
