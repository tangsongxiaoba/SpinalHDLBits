package SpinalHDLBits

package SpinalHDLBits.Fancytimer

import spinal.core._
import SpinalHDLBits.Config._
import spinal.lib.fsm._

case class Fancytimer() extends Component {
    val io = new Bundle {
        val clk      = in Bool ()
        val reset    = in Bool ()
        val data     = in Bool ()
        val ack      = in Bool ()
        val count    = out UInt (4 bits)
        val counting = out Bool ()
        val done     = out Bool ()
    }

    val clkDom = ClockDomain(
      clock = io.clk,
      reset = io.reset,
      config = ClockDomainConfig(
        resetKind = SYNC
      )
    )

    val clkArea = new ClockingArea(clkDom) {
        val fsm = new StateMachine {
            val stFsm = new StateFsm(fsm = fsm1101()) with EntryPoint
            val stSft = new StateFsm(fsm = fsmSft())
            val stCnt = new StateFsm(fsm = fsmCnt())
            val stAck = new StateFsm(fsm = fsmAck())

            val delay    = Reg(UInt(4 bits))
            val datas    = Reg(UInt(5 bits))
            val counting = Reg(Bool())
            val done     = Reg(Bool())

            io.count    := delay
            io.counting := counting
            io.done     := done

            stFsm.whenCompleted(goto(stSft))
            stSft.whenCompleted {
                datas    := delay.resize(5) + 1
                counting := True
                goto(stCnt)
            }
            stCnt.whenCompleted {
                counting := False
                done     := True
                goto(stAck)
            }
            stAck.whenCompleted {
                done := False
                goto(stFsm)
            }

            def fsm1101() = new StateMachine {
                val stA = new State with EntryPoint
                val stB = new State
                val stC = new State
                val stD = new State

                stA.whenIsActive {
                    when(io.data === True) {
                        goto(stB)
                    } otherwise (goto(stA))
                }
                stB.whenIsActive {
                    when(io.data === True) {
                        goto(stC)
                    } otherwise (goto(stA))
                }
                stC.whenIsActive {
                    when(io.data === True) {
                        goto(stB)
                    } otherwise (goto(stD))
                }
                stD.whenIsActive {
                    when(io.data === True) {
                        exit()
                    } otherwise (goto(stA))
                }
            }

            def fsmSft() = new StateMachine {
                val stCnt = new State with EntryPoint

                val cnt4 = Reg(UInt(3 bits))

                stCnt
                    .onEntry {
                        cnt4  := 0
                        delay := 0
                    }
                    .whenIsActive {
                        delay := (delay |<< 1) + io.data.asUInt
                        cnt4  := cnt4 + 1
                        when(cnt4 >= 3) {
                            exit()
                        }
                    }
            }

            def fsmCnt() = new StateMachine {
                val stCnt1000 = new State with EntryPoint

                val cnt1000 = Reg(UInt(10 bits))

                stCnt1000
                    .onEntry(cnt1000 := 1)
                    .whenIsActive {
                        cnt1000 := cnt1000 + 1
                        when(cnt1000 > 999) {
                            when(datas >= 1) {
                                cnt1000 := 1
                                delay   := delay - 1
                                datas   := datas - 1
                            } otherwise {
                                exit()
                            }
                        }
                    }
            }

            def fsmAck() = new StateMachine {
                val stAck = new State with EntryPoint

                stAck.whenIsActive {
                    when(io.ack === True) {
                        exit()
                    }
                }
            }
        }
    }

    setDefinitionName("top_module")
    noIoPrefix()
}

object FancytimerVerilog extends App {
    Config.spinal.generate(Fancytimer()).printPruned()
}
