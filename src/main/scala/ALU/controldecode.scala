package ALU
import chisel3._
import chisel3.util._
import scala.collection.immutable._
class controldecode extends Module{
    val io = IO(new Bundle{
        //-----------------input-------------
        //val jump = Input(UInt(2.W))
        val r_type= Input(Bool())
        val i_type= Input(Bool())
        val lui_type= Input(Bool())
        //val auipc_type= Input(Bool())
        val jal_type= Input(Bool())
        val jalr_type= Input(Bool())
        val Lw_type= Input(Bool())
        val s_type= Input(Bool())
        val b_type= Input(Bool())

        //val branchtrue= Input(Bool())
        //----------------------output---------------
        //val JALR = Output(Bool())
        val REGFILE = Output(Bool())
        val op_b_sel = Output(Bool())
        val imm_sel = Output(UInt(2.W))
        val mem_read =Output(Bool())
        val mem_writeback=Output(Bool())
        val mem_write=Output(Bool())
        val next_pc_selec = Output(UInt(2.W))
        val aluop = Output(UInt(3.W))
        val op_a_sel = Output(UInt(2.W))
        val branch_sel = Output(Bool()) 
        


    })
    //all boolean values are set to 0 here
    val listofbool = List(io.REGFILE,io.op_a_sel,io.op_b_sel,io.mem_read,io.mem_writeback,io.mem_write,io.op_a_sel,io.branch_sel)
    val mapp = listofbool.map(_:=0.B)
    val list2 = List(io.imm_sel,io.next_pc_selec,io.aluop).map(_:=0.U)
    //val aluop = 0.U
    when(io.r_type===1.B){
        io.REGFILE:=1.B
        //io.imm_sel:="b11".U
        io.imm_sel:="b00".U
    }.elsewhen(io.i_type===1.B){
        io.REGFILE:=1.B
        io.op_b_sel:=1.B
        io.aluop:="b001".U
    }.elsewhen(io.lui_type===1.B){
        io.REGFILE:=1.B
        io.op_b_sel:=1.B
        io.imm_sel:="b01".U
        io.aluop:="b110".U
        io.op_a_sel:="b11".U

    }.elsewhen(io.jal_type===1.B){
        io.REGFILE:=1.B
        io.imm_sel:="b00".U
        io.next_pc_selec:="b10".U
        io.aluop:="b011".U
        io.op_a_sel:="b10".U


    }.elsewhen(io.jalr_type===1.B){
        //io.JALR:=1.B
        io.REGFILE:=1.B
        io.op_a_sel:="b10".U
        io.next_pc_selec:="b11".U
        io.aluop:="b011".U

    }.elsewhen(io.Lw_type===1.B){
        io.REGFILE:=1.B
        io.op_b_sel:=1.B
        io.mem_read:=1.B
        io.mem_writeback:=1.B
        io.aluop:="b100".U




    }.elsewhen(io.s_type===1.B){
        io.mem_write:=1.B
        io.op_b_sel:=1.B
        io.imm_sel:="b01".U
        io.aluop:="b101".U


    }.elsewhen(io.b_type===1.B){
        io.branch_sel:=1.B
        io.aluop:="b010".U
        io.next_pc_selec:="b01".U

    // }.elsewhen(io.auipc_type===1.B){
    //     io.REGFILE:=1.B
    //     io.op_b_sel:=1.B
    //     io.aluop:="b101".U
    //     io.imm_sel:="b10".U
    //     io.op_a_sel:="b01".U
    // }
    

}
}