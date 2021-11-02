package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class controldecodeTest extends FreeSpec with ChiselScalatestTester{
    
    "control decode Test" in {
        test(new controldecode()){c=>
        c.io.r_type.poke(0.B)
        c.io.i_type.poke(0.B)
        c.io.lui_type.poke(0.B)
        c.io.jal_type.poke(0.B)
        c.io.jalr_type.poke(0.B)
        c.io.Lw_type.poke(0.B)
        c.io.s_type.poke(0.B)
        c.io.b_type.poke(1.B)
        //c.io.auipc_type.poke(0.B)
        //c.io.bnew_type.poke(0.B)
        c.clock.step(20)
        //-----------------------
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
        c.io.branch_sel.expect(1.B)


        }

    }
}