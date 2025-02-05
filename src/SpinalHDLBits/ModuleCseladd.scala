package SpinalHDLBits

package SpinalHDLBits.ModuleCseladd

import spinal.core._
import SpinalHDLBits.Config._

case class ModuleCseladd() extends Component {
    val io = new Bundle {
        val a, b = in Bits (32 bits)
        val sum  = out Bits (32 bits)
    }
    
    setDefinitionName("top_module")
    noIoPrefix()
    
    val add16Hi0 = new Add16
    val add16Hi1 = new Add16
    val add16Lo = new Add16

    add16Lo.io.cin.clear()
    add16Hi0.io.cin.clear()
    add16Hi1.io.cin.set()

    add16Hi1.io.a := io.a(31 downto 16) 
    add16Hi1.io.b := io.b(31 downto 16) 
    add16Hi0.io.a := io.a(31 downto 16)
    add16Hi0.io.b := io.b(31 downto 16)
    add16Lo.io.a := io.a(15 downto 0) 
    add16Lo.io.b := io.b(15 downto 0) 

    io.sum := (Mux(add16Lo.io.cout, add16Hi1.io.sum, add16Hi0.io.sum), add16Lo.io.sum).asBits
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

object ModuleCseladdVerilog extends App {
    Config.spinal.generate(ModuleCseladd()).printPruned()
}
