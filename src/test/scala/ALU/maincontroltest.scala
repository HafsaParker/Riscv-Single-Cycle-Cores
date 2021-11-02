package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class maincontroltest extends FreeSpec with ChiselScalatestTester{
    "main control test" in {
        test(new maincontrol()){c=>
        c.io.opcode.poke("b1100011".U)

        c.clock.step(20)
        //c.io.JALR.expect(0.B)
        c.io.REGFILE.expect(0.B)
        c.io.op_b_sel.expect(0.B)
        c.io.imm_sel.expect("b00".U)
        c.io.mem_read.expect(0.B)
        c.io.mem_writeback.expect(0.B)
        c.io.mem_write.expect(0.B)
        c.io.next_pc_selec.expect("b01".U)
        c.io.aluop.expect("b010".U)
        c.io.op_a_sel.expect("b00".U)
        
        c.io.branch_out.expect(1.B)

        }
    }

}