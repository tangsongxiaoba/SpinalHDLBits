package SpinalHDLBits

package SpinalHDLBits.Bcdadd100

import spinal.core._
import SpinalHDLBits.Config._

case class Bcdadd100() extends Component {
    val io = new Bundle {
        val a, b = in Bits (400 bits)
        val cin  = in Bool ()
        val sum  = out Bits (400 bits)
        val cout = out Bool ()
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val bcdFaddArray = Array.tabulate(100)(_ => new BcdFadd())

    val carryIn = Bits(101 bits).noCombLoopCheck()

    carryIn(0) <> io.cin

    bcdFaddArray.indices.foreach(i =>
        (
          bcdFaddArray(i).io.a <> io.a(i * 4 + 3 downto (i * 4)),
          bcdFaddArray(i).io.b <> io.b(i * 4 + 3 downto (i * 4)),
          bcdFaddArray(i).io.sum <> io.sum(i * 4 + 3 downto (i * 4)),
          bcdFaddArray(i).io.cin <> carryIn(i),
          bcdFaddArray(i).io.cout <> carryIn(i + 1)
        )
    )

    io.cout <> carryIn(100)

}

class BcdFadd extends BlackBox {
    val io = new Bundle {
        val a, b = in Bits (4 bits)
        val cin  = in Bool ()
        val sum  = out Bits (4 bits)
        val cout = out Bool ()
    }
    noIoPrefix()
    setBlackBoxName("bcd_fadd")
}

object Bcdadd100Verilog extends App {
    Config.spinal.generate(Bcdadd100()).printPruned()
}
