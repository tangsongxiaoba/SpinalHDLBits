package SpinalHDLBits

package SpinalHDLBits.ModuleAddSub

import spinal.core._
import SpinalHDLBits.Config._

case class ModuleAddSub() extends Component {
    val io = new Bundle {
        val a, b = in Bits (32 bits)
        val sub  = in Bool ()
        val sum  = out Bits (32 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val add16Hi = new Add16
    val add16Lo = new Add16

    val bXorSub = io.b ^ io.sub#* 32

    add16Lo.io.cin <> io.sub
    add16Hi.io.cin <> add16Lo.io.cout

    (add16Hi.io.a, add16Lo.io.a) := io.a
    (add16Hi.io.b, add16Lo.io.b) := bXorSub

    io.sum := (add16Hi.io.sum, add16Lo.io.sum).asBits
}

class Add16 extends BlackBox {
    val io = new Bundle {
        val a, b = in Bits (16 bits)
        val cin  = in Bool ()
        val sum  = out Bits (16 bits)
        val cout = out Bool ()
    }
    noIoPrefix()
    setBlackBoxName("add16")
}

object ModuleAddSubVerilog extends App {
    Config.spinal.generate(ModuleAddSub()).printPruned()
}
