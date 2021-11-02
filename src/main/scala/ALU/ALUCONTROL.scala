package ALU
import chisel3._ 
import chisel3.util._

class AluControl extends Module with Config{
    val io = IO(new Bundle{
        val input_func3 = Input(datatype(func3Width.W))
        val input_func7 = Input(Bool())
        val input_opcode = Input(datatype(3.W))
        val output_control = Output(datatype(4.W))
    })
    
    // io.output_control := 0.U
    // val lst1 = io.input_opcode.asBools.toList
    // val lst2 = io.input_func3.asBools.toList

    // val firstor = ~lst1(2) & ~lst1(0) & ~lst2(2) & lst2(1) | lst1(1) & lst1(2) 
    // val secondor = ~lst2(1) & lst2(0) | ~lst2(1) & lst2(2) | lst1(1) & lst1(2)
    // val thirdor = ~lst2(1) & ~lst2(2) & lst2(0) | lst2(2) & io.input_func7 | lst2(2) & lst2(1) | lst1(1) & lst1(2)
    // val fourthor = ~lst2(2) & io.input_func7 | ~lst2(2) & lst2(0) | ~lst2(1) & lst2(0) & ~io.input_func7 | ~lst2(0) & lst2(1) & lst2(2) | lst1(0) & lst1(1) & lst1(2)
    
    // io.output_control := Cat(firstor,secondor,thirdor,fourthor)
    val func3_check = io.input_func3 ==="b101".U
    io.output_control:=MuxCase(0.U,Array(
        (io.input_opcode==="b000".U) -> Cat(io.input_func7,io.input_func3),//rtype
        (io.input_opcode==="b100".U)->"b0000".U, //loadtype
        (io.input_opcode==="b101".U)->"b0000".U,//stype
        (io.input_opcode==="b011".U)->"b1111".U, //jalrtype
        (io.input_opcode==="b110".U) ->"b0000".U,//lui
        (io.input_opcode==="b001".U)-> Mux(func3_check,Cat(io.input_func7,io.input_func3),Cat("b0".U,io.input_func3))




        
        // (io.input_opcode==="b001" && input_func3==="b101".U) -> Cat(io.input_func7,io.input_func3),
        // (io.input_opcode==="b001")->Cat()
    ))




}