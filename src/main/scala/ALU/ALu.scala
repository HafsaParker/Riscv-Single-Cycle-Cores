package ALU
import chisel3._
import chisel3.util._

class ALu extends Module with Config{
    val io = IO(new Bundle{
        val input_a = Input(alutype(WLEN.W))
        val input_b = Input(alutype(WLEN.W))
        val sel = Input(UInt(4.W))
        val out  = Output(alutype(WLEN.W))
    })
    val adder = io.input_a + io.input_b
    val subtractor = io.input_a - io.input_b
    val andgate = io.input_a & io.input_b
    val orgate = io.input_a |io.input_b
    val xorgate = io.input_a ^ io.input_b
    val leftShifter = (io.input_a << io.input_b(4,0))
    val shiftrightLogical = (io.input_a >> io.input_b(4,0))
    val slt = (io.input_a < io.input_b)
    val sltu = (io.input_a.asUInt < io.input_b.asUInt)
    val rightShiftAR = ((io.input_a.asUInt >> io.input_b(4,0).asUInt)).asSInt
    io.out:=0.S


    io.out := MuxCase(0.S,Array(
        (io.sel === 0.U) -> adder,
        (io.sel === 1.U) -> leftShifter,
        (io.sel === 2.U) -> Mux(slt,1.S,0.S),
        (io.sel === 3.U) -> Mux(sltu,1.S,0.S),
        (io.sel === 4.U) -> xorgate,
        (io.sel === 5.U) -> shiftrightLogical,
        (io.sel === 6.U) -> orgate,
        (io.sel === 7.U) -> andgate,
        (io.sel === 8.U) -> subtractor,
        (io.sel === 13.U) -> rightShiftAR
//(io.sel === 1.U) -> subtractor,
//(io.sel === 2.U) -> andgate,
//(io.sel === 3.U) -> orgate,
//(io.sel === 6.U) -> rightShiftAR,
//(io.sel === 7.U) -> leftShifter,
//(io.sel === 8.U) -> Mux(slt,1.S,0.S)
//(io.sel === 9.U) -> Mux(sltu,1.S,0.S)
    ))
    
    
}
