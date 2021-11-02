 package ALU
import chisel3._
import scala.collection.immutable._



class TypeDecode extends Module with Config{

	val  io = IO(new Bundle {

		val opcode = Input(UInt(opcodewidth.W))
		//val branch_alu_out  =Input(Bool())
		val rType = Output(Bool())
		val load = Output(Bool())
		val store = Output(Bool())
		val bType = Output(Bool()) // branch
		val iType = Output(Bool())
		val jalr = Output(Bool())
		val jal = Output(Bool())
		val lui = Output(Bool())
		val auipc=Output(Bool())
		//val branch_true=Output(Bool())
	})
	//,io.branch_true
    
    Seq(io.rType, io.load, io.store, io.bType, io.iType, io.jalr, io.jal, io.lui,io.auipc) map (_ := 0.B)
	

	

	when(io.opcode === "b0110011".U){
		io.rType := 1.B
	}
    
    .elsewhen(io.opcode === "b0000011".U){
		io.load := 1.B
	}
    
    .elsewhen(io.opcode === "b0100011".U){
		io.store := 1.B
	}
    
    .elsewhen(io.opcode === "b1100011".U){
		io.bType := 1.B
	}
    
    .elsewhen(io.opcode === "b0010011".U){
		io.iType := 1.B
	}
    
    .elsewhen(io.opcode === "b1100111".U){
		io.jalr := 1.B
	}
    
    .elsewhen(io.opcode === "b1101111".U){
		io.jal := 1.B
	}
    
    .elsewhen(io.opcode === "b0110111".U){
		io.lui := 1.B
	}
	.elsewhen(io.opcode==="b0010111".U){
		io.auipc:=1.B
	}
	//io.branch_true := io.branch_alu_out
	
	
}
