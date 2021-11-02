package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class writebackTest extends FreeSpec with ChiselScalatestTester{
    "write back Test" in{
        test(new writeback()){c=>
        c.io.mux1_sp.poke(0.B)
        c.io.mux2_sp.poke(1.B)
        c.io.Idatamem_mux1_input1.poke(2.U)
        c.io.IALU_mux1_input0.poke(1.U)
        c.io.pc4_mux2_input1.poke(5.U)
        c.clock.step(20)
        c.io.writeback.expect(5.U)

        }
    }
}    
