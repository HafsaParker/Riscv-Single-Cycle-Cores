package ALU
import chisel3._
// import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile


class instructionmem extends Module with Config {
val io = IO(new Bundle{
    val addr = Input(datatype(10.W))
    val inst = Output(datatype( WLEN.W))
})

val imem = Mem(INST_MEM_LEN,UInt(WLEN.W))
loadMemoryFromFile(imem, initFile)
io.inst := imem (io.addr)
// println(io.inst)
// println(io.addr)
}