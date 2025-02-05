package SpinalHDLBits

package SpinalHDLBits.Config

import spinal.core._
import spinal.core.sim._

object Config {
    def spinal = SpinalConfig(
      targetDirectory = "gen",
      headerWithDate = true,
      mode = Verilog,
      verbose = true
    )
    def sim = SimConfig.withConfig(spinal).withFstWave
}


