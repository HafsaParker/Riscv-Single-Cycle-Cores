package ALU
import chisel3._
import chisel3.util._
trait Config{ 
    val WLEN = 32
    //-----ALU----------
    val alutype = SInt
    ///____alucontrol_______
    //val alucontrolType = UInt
    val func3Width = 3
    //-------main-------
    val datatype = UInt
    //------instructionmem----------
     val INST_MEM_LEN = 1024
     val initFile = "/home/hafsa/abc.txt"
    //------maicontrol--------
    val opcodewidth  = 7
    //-------registerfile--------
    val REGFILE_LEN = 32
    val r_adresswidth = 5
    
    





}