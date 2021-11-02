package ALU
import chisel3._
import chisel3.util._
import scala.collection.immutable._

class maincontrol extends Module with Config{
    val io = IO(new Bundle{
        val opcode = Input(datatype(opcodewidth.W))
        //-----------output------------------
        //val JALR = Output(Bool())
        val REGFILE = Output(Bool())
        val op_b_sel = Output(Bool())
        val imm_sel = Output(datatype(2.W))
        val mem_read =Output(Bool())
        val mem_writeback=Output(Bool())
        val mem_write=Output(Bool())
        val next_pc_selec = Output(datatype(2.W))
        val aluop = Output(datatype(3.W))
        val op_a_sel = Output(datatype(2.W))
        val branch_out = Output(Bool())

    })
    val TD = Module(new TypeDecode())
    val CD = Module(new controldecode())
    // TD.io.opcode:=io.opcode
    // CD.io.r_type:= TD.io.rType
    // CD.io.Lw_type:=  TD.io.load
    // CD.io.s_type:= TD.io.store
    // CD.io.b_type:= TD.io.bType
    // CD.io.i_type:= TD.io.iType
    // CD.io.jalr_type:=  TD.io.jalr
    // CD.io.jal_type:=   TD.io.jal
    // CD.io.lui_type :=   TD.io.lui
    // //CD.io.auipc_type := TD.io.auipc
    // //CD.io.bnew_type:=TD.io.bOut


    // io.JALR:= CD.io.JALR
    // io.REGFILE:= CD.io.REGFILE
    // io.op_b_sel:= CD.io.op_b_sel
    // io.imm_sel:= CD.io.imm_sel
    // io.mem_read:= CD.io.mem_read
    // io.mem_writeback:= CD.io.mem_writeback
    // io.mem_write:=CD.io.mem_write
    // io.next_pc_selec:= CD.io.next_pc_selec
    // io.aluop:= CD.io.aluop
    // io.op_a_sel:= CD.io.op_a_sel
    // io.branch_out:=CD.io.branch_sel




// }





    val list_of_typedecode=Seq(io.opcode,TD.io.rType,TD.io.load,TD.io.store,TD.io.bType,TD.io.iType,TD.io.jalr,TD.io.jal,TD.io.lui)
    val list_of_Controldecode = Seq(TD.io.opcode,CD.io.r_type,CD.io.Lw_type,CD.io.s_type,CD.io.b_type,CD.io.i_type,CD.io.jalr_type,CD.io.jal_type,CD.io.lui_type)
    (list_of_Controldecode zip  list_of_typedecode).map{x=>x._1:=x._2}
    val list1= Seq(io.REGFILE,io.op_b_sel,io.imm_sel,io.mem_read,io.mem_writeback,io.mem_write,io.next_pc_selec,io.aluop,io.op_a_sel,io.branch_out)
    val list2 =Seq(CD.io.REGFILE,CD.io.op_b_sel,CD.io.imm_sel,CD.io.mem_read,CD.io.mem_writeback,CD.io.mem_write,CD.io.next_pc_selec,CD.io.aluop,CD.io.op_a_sel,CD.io.branch_sel)
    (list1 zip list2).map{x=>x._1:=x._2}


}