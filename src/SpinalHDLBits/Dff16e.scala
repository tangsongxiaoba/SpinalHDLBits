package SpinalHDLBits

package SpinalHDLBits.Dff16e

import spinal.core._
import SpinalHDLBits.Config._
import spinal.lib.HIGHER_FIRST

case class Dff16e() extends Component {
    val io = new Bundle {
        val clk     = in Bool ()
        val resetn  = in Bool ()
        val d       = in Bits (16 bits)
        val byteena = in Bits (2 bits)
        val q       = out Bits (16 bits)
    }

    setDefinitionName("top_module")
    noIoPrefix()

    val myClockDomain = ClockDomain(
      clock = io.clk,
      reset = io.resetn,
      config = ClockDomainConfig(
        resetKind = SYNC,
        resetActiveLevel = LOW
      )
    )

    val clockArea = new ClockingArea(myClockDomain) {
        io.q(7 downto 0)  := RegNextWhen(io.d(7 downto 0), io.byteena(0)) init 0
        io.q(15 downto 8) := RegNextWhen(io.d(15 downto 8), io.byteena(1)) init 0
    }

}

object Dff16eVerilog extends App {
    Config.spinal.generate(Dff16e()).printPruned()
}
