package unit1.OtherExamples;
/**
 * A tester for the Java primitive types.
 * 
 * Primitive Data Types
 * ====================
 * 
 * Keyword	   Description			            Size/Format
 * -------	   ------------------------------   ----------------------- 
 * (integral types)
 * byte	       Byte-length integer		        8-bit two's complement 
 * short	   Short integer		            16-bit two's complement 
 * int	       Integer			                32-bit two's complement 
 * long	       Long integer			            64-bit two's complement
 * 
 * char	       A single character		        16-bit Unicode character
 * 
 * (floating-point types for real numbers)
 * float	   Single-precision floating point  32-bit IEEE 754
 * double	   Double-precision floating point  64-bit IEEE 754
 * 
 * (other types)
 * boolean	   A boolean value (true or false)  true or false 
 *
 *
 * @author Mark Jones
*/

public class PrimitiveTester
{
	/**
	 * Application that tests Java primitive data types.
	 * @param args   no args expected
	 */
	public static void main(String[] args) {

        // *************  integral types  ***************
		
        System.out.print("The largest 'byte' value (Byte.MAX_VALUE) is " + Byte.MAX_VALUE + " // ");
        System.out.println("2^7 - 1 = " + (byte)(Math.pow(2, 7) - 1));        
        System.out.print("The smallest 'byte' value (Byte.MIN_VALUE) is " + Byte.MIN_VALUE + " // ");
        System.out.println("-(2^7) = " + (byte)(-Math.pow(2, 7)));
        
        System.out.print("\nThe largest 'short' value (Short.MAX_VALUE) is " + Short.MAX_VALUE + " // ");
        System.out.println("2^15 - 1 = " + (short)(Math.pow(2, 15) - 1));
        System.out.print("The smallest 'short' value (Short.MIN_VALUE) is " + Short.MIN_VALUE + " // ");
        System.out.println("-(2^15) = " + (short)(-Math.pow(2, 15)));
        
        System.out.print("\nThe largest 'int' value (Integer.MAX_VALUE) is " + Integer.MAX_VALUE + " // ");
        System.out.println("2^31 - 1 = " + (int)(Math.pow(2, 31) - 1));
        System.out.print("The smallest 'int' (Integer.MIN_VALUE) value is " + Integer.MIN_VALUE + " // ");
        System.out.println("-(2^31) = " + (int)(-Math.pow(2, 31)));
        
        System.out.print("\nThe largest 'long' value (Long.MAX_VALUE) is " + Long.MAX_VALUE + " // ");
        System.out.println("2^63 - 1 = " + (long)(Math.pow(2, 63) - 1));
        System.out.print("The smallest 'long' value (Long.MIN_VALUE) is " + Long.MIN_VALUE + " // ");
        System.out.println("-(2^63) = " + (long)(-Math.pow(2, 63)));
        
        // The char data type is a single 16-bit Unicode character. 
        // It has a minimum value of '\u0000' (or 0).
        // It has a maximum value of '\uffff' (or 65,535 inclusive).
        
        System.out.print("\nThe largest 'char' value (Character.MAX_VALUE) is " + (int)Character.MAX_VALUE + " // "); 
        System.out.println("the character is \'" + Character.MAX_VALUE + "\'");
        System.out.print("The smallest 'char' value (Character.MIN_VALUE) is " + (int)Character.MIN_VALUE + " // ");
        System.out.println("the character is \'" + Character.MIN_VALUE + "\'"); 
        
        // *********  floating-point types (for approximating real numbers) *********
        
        // The IEEE 754 spec for floating-point numbers is a bit complicated.
        // Single-precision is a sign bit, 8 exponent bits, and a 24 bit mantissa (only 23
        // of the 24 bits are explicitly stored, the leftmost 1 is implicit.
        //
        //      seeeeeeeeddddddddddddddddddddddd
        //      ^                              ^
        //     bit 31                         bit 0
        //
        // The 8 exponent bits represent an 8 bit unsigned integer (from 0 to 255)
        // which is "biased" (offset) by 127 (0x7F).  An exponent of 0x00 is a special case 
        // indicating denormalized values (the implicit bit is 0 and the exponent is -126).
        // For exponents between 0x01 and 0xFE (normalized values), you have to subtract 
        // 127 (0x7F) from the exponents to get the actual value, so the exponent actually 
        // ranges from -126 (0x01 - 0x7F) to 127 (0xFE - 0x7F).

        // If the exponent is 00 (all 0's), then the (denormalized) number represented is
        //      (-1)^s x 2^(1-127) x 0.ddddddddddddddddddddddd
        //
        // If the exponent is between 01 and FE, then the (normalized) number represented is
        //      (-1)^s x 2^(eeeeeeee-127) x 1.ddddddddddddddddddddddd
        //
        // If the exponent is FF (all 1's), and the mantissa is zero, it represent infinity.
        //      01111111100000000000000000000000  +infinity
        //      11111111100000000000000000000000  -infinity

        // The greatest value that can be represented in single precision, 
        // approximately 3.4028235 × 10^38, is 1.11111111111111111111111b × 2^(11111110b-127).
        // The smallest (denormalized) value that can be represented in single precision,
        // is 2^-126 x 2^-23 = 2^-149
        
        System.out.print("\nThe largest positive 'float' value (Float.MAX_VALUE) is " + Float.MAX_VALUE + " // ");
//        System.out.println("1.11111111111111111111111b × 2^(11111110b-127) = " +
//        	 (float)((1 + 0b11111111111111111111111 * Math.pow(2, -23)) * Math.pow(2, 0b11111110 - 127)));
        System.out.println("(2 - 2^-23) × 2^127 = " + 
        			 (float)(2 - Math.pow(2, -23)) * Math.pow(2, 127));
        System.out.print("The smallest positive 'float' value  (Float.MIN_VALUE) is " + Float.MIN_VALUE + " // ");
        System.out.println("2^-149 = " + (float)(Math.pow(2, -149)));   
        
        // Double-precision is a sign bit, 11 exponent bits, and a 53 bit mantissa (only 52
        // of the 53 bits are explicitly stored, the leftmost 1 is implicit.
        //
        //      seeeeeeeeeeedddddddddddddddddddddddddddddddddddddddddddddddddddd
        //      ^                                                              ^
        //     bit 63                                                         bit 0
        //
        // The 11 exponent bits represent an 11 bit unsigned integer (from 0 to 2047)
        // which is "biased" (offset) by 1023 (0x3FF).  An exponent of 0x000 is a special case 
        // indicating denormalized values (the implicit bit is 0 and the exponent is -1022).
        // For exponents between 0x001 and 0x7FE (normalized values), you have to subtract 
        // 1023 (0x3FF) from the exponents to get the actual value, so the exponent actually 
        // ranges from -1022 (0x001 - 0x3FF) to 1023 (0x7FE - 0x3FF).

        // If the exponent is 00 (all 0's), then the (denormalized) number represented is
        //      (-1)^s x 2^(1-1023) x 0.dddddddddddddddddddddddddddddddddddddddddddddddddddd
        //
        // If the exponent is between 01 and FE, then the (normalized) number represented is
        //      (-1)^s x 2^(eeeeeeeeeee-1023) x 1.dddddddddddddddddddddddddddddddddddddddddddddddddddd
        //
        // If the exponent is 7FF (all 1's), and the mantissa is zero, it represent infinity.
        //      seeeeeeeeeeedddddddddddddddddddddddddddddddddddddddddddddddddddd
        //      0111111111110000000000000000000000000000000000000000000000000000  +infinity
        //      1111111111110000000000000000000000000000000000000000000000000000  -infinity

        // The greatest value that can be represented in double precision, 
        // approximately 3.4028235 × 10^38, is 1.11111111111111111111111b × 2^(11111110b-127).
        // The smallest (denormalized) value that can be represented in double precision,
        // is 2^-1022 x 2^-51 = 2^-1073
 
        System.out.print("\nThe largest positive 'double' value (Double.MAX_VALUE) is " + Double.MAX_VALUE + " // ");
//        System.out.println("\n1.11111111111111111111111b × 2^(11111110b-127) = " +
//           	 (double)((1 + 0b1111111111111111111111111111111111111111111111111111 * Math.pow(2, -52)) 
//           			 * Math.pow(2, 0b11111111110 - 1023)));
        System.out.println("(2 - 2^-52) × 2^1023 = " + 
   			 (double)(2 - Math.pow(2, -52)) * Math.pow(2, 1023));
        System.out.print("The smallest positive 'double' value (Double.MIN_VALUE) is " + Double.MIN_VALUE + " // ");
        System.out.println("2^-1073 = " + (double)(Math.pow(2, -1073)) + " [WRONG???]");   
        
        // *********  boolean type (for logical values true and false) *********

        System.out.print("\nThe 'boolean' value Boolean.TRUE is " + Boolean.TRUE + " // ");
        System.out.println("the 'boolean' value Boolean.FALSE is " + Boolean.FALSE);
	}
}

