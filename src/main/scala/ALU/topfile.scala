package ALU
import chisel3._
import chisel3.util._

class topfile extends Module with Config{
    val io = IO(new Bundle{
        val data_OUT = Output(SInt(WLEN.W))
    })
    //val aluControl = Module(new alucontrol())
    val aluControl = Module(new AluControl())
    val alu = Module(new ALu())
    val branchcontrol=Module (new branchAlu())
    val control = Module(new maincontrol())
    val data_mem = Module(new DataMemory(new Parameters(35,5)))
    //val immediate_gen = Module(new ImmediateGen(UInt(32.W),SInt(32.W)))
    val immediate_gen = Module(new ImmediateGeneration())
    val instruct_memory = Module(new instructionmem())
    val pc_counter = Module(new pc())
    val RFile = Module(new RegFile())
    val jaLr = Module(new jalr())
    //---------pc_counter------------
    pc_counter.io.inpuT:=pc_counter.io.pc_plus4
    //-----------instruction memory--------
    instruct_memory.io.addr:=pc_counter.io.out(11,2)
    //-----------registerfile----------
         //----databits are comming from instruction mem-----
    RFile.io.raddr1:=instruct_memory.io.inst(19,15)
    RFile.io.raddr2:=instruct_memory.io.inst(24,20)
    RFile.io.waddr:=instruct_memory.io.inst(11,7)
    RFile.io.wen:=control.io.REGFILE

    //---(yaha abhi rd add nai kia bcz wo mux se arha ha)
    //---------------CONTROL--------------------
        //----data bits are cmming from instruction mem
    control.io.opcode:=instruct_memory.io.inst(6,0)
    //----------IMMIDIATECONTROL-----------------
         //------bits are comming from instruction mem and pc counter--------
    immediate_gen.io.instruction:=instruct_memory.io.inst
    immediate_gen.io.pc:=pc_counter.io.out

    
    //-----------ALUCONTROL--------------------
         //-----bits are commin from instruction mem and
    aluControl.io.input_func3:=instruct_memory.io.inst(14,12)
    aluControl.io.input_func7:=instruct_memory.io.inst(30)
    aluControl.io.input_opcode:=control.io.aluop
    // aluControl.io.Aluop:=control.io.aluop
    // aluControl.io.func3:=instruct_memory.io.inst(14,12)
    // aluControl.io.func7:=instruct_memory.io.inst(30)
    
    //-----------------ALU input module------------------------
          //-----input A------------------
    when(control.io.op_a_sel==="b10".U){
        alu.io.input_a:=pc_counter.io.pc_plus4
    }.otherwise{
        alu.io.input_a:=RFile.io.rdata1
    }
         //------input B--------------------
    // alu.io.input_b:=  Mux(control.io.op_b_sel,MuxCase(0.S,Array(
    //     (control.io.imm_sel === "b00".U & immediate_gen.io.instr(6,0)==="b0010011".U) -> immediate_gen.io.immd_se,
    //     (control.io.imm_sel === "b01".U & immediate_gen.io.instr(6,0)==="b0100011".U) -> immediate_gen.io.immd_se,
    //     (control.io.imm_sel === "b01".U & immediate_gen.io.instr(6,0)==="b0010111".U) -> immediate_gen.io.immd_se)),RFile.io.rdata2)
    alu.io.input_b:=  Mux(control.io.op_b_sel,MuxCase(0.S,Array(
         (control.io.imm_sel === "b00".U ) -> immediate_gen.io.i_imm,
        (control.io.imm_sel === "b01".U) -> immediate_gen.io.s_imm,
        (control.io.imm_sel === "b01".U ) -> immediate_gen.io.u_imm)),RFile.io.rdata2)


         //-----------select pin for ALU----------
    alu.io.sel:= aluControl.io.output_control
         //------------branch and jal to control pc -----------
    //---------JALR-----------------
    jaLr.io.x0:=RFile.io.rdata1

    //when(immediate_gen.io.instr(6,0)==="b0010011".U){
    

    jaLr.io.Typee:=immediate_gen.io.i_imm

    
    
    //-------------Branch ALU ------------------
    branchcontrol.io.arg_x:=RFile.io.rdata1
    branchcontrol.io.arg_y:=RFile.io.rdata2
    branchcontrol.io.func3:=instruct_memory.io.inst(14,12)
   
    val branch_alu_out_and_control_branchoutput=branchcontrol.io.br_taken && control.io.branch_out //(upper wale mux ki 1 select pin)
    //(upper wala mux data path ka)
    //(yaha se check krwana ha shahzaib ko)
    val mux_high1=Mux(branch_alu_out_and_control_branchoutput,immediate_gen.io.sb_imm,pc_counter.io.pc_plus4)
    pc_counter.io.inpuT := MuxCase(0.S,Array(
        (control.io.next_pc_selec==="b00".U) -> pc_counter.io.pc_plus4,
        //(control.io.next_pc_selec==="b01".U & mux_high1) -> mux_high1,
        (control.io.next_pc_selec==="b01".U ) -> mux_high1,
        
        (control.io.next_pc_selec==="b10".U ) -> immediate_gen.io.uj_imm,
        (control.io.next_pc_selec==="b11".U ) -> jaLr.io.OutPut))
        
    //println(pc_counter.io.inpuT )
    //------------------DATA_MEMORY---------------------------------
    data_mem.io.addr:=alu.io.out(11,2).asUInt
    data_mem.io.data_in:=RFile.io.rdata2
    data_mem.io.wr_en:=control.io.mem_write
    //-----------front mux-----------------------------------
    RFile.io.wdata :=Mux(control.io.mem_writeback,data_mem.io.data_out,alu.io.out)

    //_________________ab output kia hoga________________________--
    io.data_OUT:=RFile.io.wdata
}