/*     */ package backend;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Matrix
/*     */ {
/*     */   private final int M;
/*     */   private final int N;
/*     */   private final double[][] data;
/*     */   
/*     */   public Matrix(int M, int N) {
/*  20 */     this.M = M;
/*  21 */     this.N = N;
/*  22 */     this.data = new double[M][N];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix(double[][] data) {
/*  28 */     this.M = data.length;
/*  29 */     this.N = (data[0]).length;
/*  30 */     this.data = new double[this.M][this.N];
/*  31 */     for (int i = 0; i < this.M; i++) {
/*  32 */       for (int j = 0; j < this.N; j++) {
/*  33 */         this.data[i][j] = data[i][j];
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private Matrix(Matrix A) {
/*  39 */     this(A.data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Matrix random(int M, int N) {
/*  45 */     Matrix A = new Matrix(M, N);
/*  46 */     for (int i = 0; i < M; i++) {
/*  47 */       for (int j = 0; j < N; j++)
/*  48 */         A.data[i][j] = Math.random(); 
/*  49 */     }  return A;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Matrix identity(int N) {
/*  55 */     Matrix I = new Matrix(N, N);
/*  56 */     for (int i = 0; i < N; i++)
/*  57 */       I.data[i][i] = 1.0D; 
/*  58 */     return I;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void swap(int i, int j) {
/*  64 */     double[] temp = this.data[i];
/*  65 */     this.data[i] = this.data[j];
/*  66 */     this.data[j] = temp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix transpose() {
/*  72 */     Matrix A = new Matrix(this.N, this.M);
/*  73 */     for (int i = 0; i < this.M; i++) {
/*  74 */       for (int j = 0; j < this.N; j++)
/*  75 */         A.data[j][i] = this.data[i][j]; 
/*  76 */     }  return A;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix plus(Matrix B) {
/*  82 */     Matrix A = this;
/*  83 */     if (B.M != A.M || B.N != A.N)
/*  84 */       throw new RuntimeException("Illegal matrix dimensions."); 
/*  85 */     Matrix C = new Matrix(this.M, this.N);
/*  86 */     for (int i = 0; i < this.M; i++) {
/*  87 */       for (int j = 0; j < this.N; j++)
/*  88 */         C.data[i][j] = A.data[i][j] + B.data[i][j]; 
/*  89 */     }  return C;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix minus(Matrix B) {
/*  95 */     Matrix A = this;
/*  96 */     if (B.M != A.M || B.N != A.N)
/*  97 */       throw new RuntimeException("Illegal matrix dimensions."); 
/*  98 */     Matrix C = new Matrix(this.M, this.N);
/*  99 */     for (int i = 0; i < this.M; i++) {
/* 100 */       for (int j = 0; j < this.N; j++)
/* 101 */         C.data[i][j] = A.data[i][j] - B.data[i][j]; 
/* 102 */     }  return C;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eq(Matrix B) {
/* 108 */     Matrix A = this;
/* 109 */     if (B.M != A.M || B.N != A.N)
/* 110 */       throw new RuntimeException("Illegal matrix dimensions."); 
/* 111 */     for (int i = 0; i < this.M; i++) {
/* 112 */       for (int j = 0; j < this.N; j++)
/* 113 */       { if (A.data[i][j] != B.data[i][j])
/* 114 */           return false;  } 
/* 115 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix times(Matrix B) {
/* 121 */     Matrix A = this;
/* 122 */     if (A.N != B.M)
/* 123 */       throw new RuntimeException("Illegal matrix dimensions."); 
/* 124 */     Matrix C = new Matrix(A.M, B.N);
/* 125 */     for (int i = 0; i < C.M; i++) {
/* 126 */       for (int j = 0; j < C.N; j++)
/* 127 */       { for (int k = 0; k < A.N; k++)
/* 128 */           C.data[i][j] = C.data[i][j] + A.data[i][k] * B.data[k][j];  } 
/* 129 */     }  return C;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix solve(Matrix rhs) {
/* 135 */     if (this.M != this.N || rhs.M != this.N || rhs.N != 1) {
/* 136 */       throw new RuntimeException("Illegal matrix dimensions.");
/*     */     }
/*     */     
/* 139 */     Matrix A = new Matrix(this);
/* 140 */     Matrix b = new Matrix(rhs);
/*     */ 
/*     */     
/* 143 */     for (int i = 0; i < this.N; i++) {
/*     */ 
/*     */ 
/*     */       
/* 147 */       int max = i; int k;
/* 148 */       for (k = i + 1; k < this.N; k++) {
/* 149 */         if (Math.abs(A.data[k][i]) > Math.abs(A.data[max][i]))
/* 150 */           max = k; 
/* 151 */       }  A.swap(i, max);
/* 152 */       b.swap(i, max);
/*     */ 
/*     */       
/* 155 */       if (A.data[i][i] == 0.0D) {
/* 156 */         throw new RuntimeException("Matrix is singular.");
/*     */       }
/*     */       
/* 159 */       for (k = i + 1; k < this.N; k++) {
/* 160 */         b.data[k][0] = b.data[k][0] - b.data[i][0] * A.data[k][i] / A.data[i][i];
/*     */       }
/*     */       
/* 163 */       for (k = i + 1; k < this.N; k++) {
/*     */         
/* 165 */         double m = A.data[k][i] / A.data[i][i];
/* 166 */         for (int n = i + 1; n < this.N; n++)
/*     */         {
/* 168 */           A.data[k][n] = A.data[k][n] - A.data[i][n] * m;
/*     */         }
/* 170 */         A.data[k][i] = 0.0D;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 175 */     Matrix x = new Matrix(this.N, 1);
/* 176 */     for (int j = this.N - 1; j >= 0; j--) {
/*     */       
/* 178 */       double t = 0.0D;
/* 179 */       for (int k = j + 1; k < this.N; k++)
/* 180 */         t += A.data[j][k] * x.data[k][0]; 
/* 181 */       x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
/*     */     } 
/* 183 */     return x;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 189 */     String returnString = "";
/* 190 */     for (int i = 0; i < this.data.length; i++) {
/*     */       
/* 192 */       for (int j = 0; j < (this.data[i]).length; j++)
/*     */       {
/* 194 */         returnString = String.valueOf(returnString) + this.data[i][j] + " ";
/*     */       }
/* 196 */       returnString = String.valueOf(returnString) + "\n";
/*     */     } 
/* 198 */     return returnString;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toPrettyString() {
/* 203 */     String returnString = "";
/* 204 */     for (int i = 0; i < this.data.length; i++) {
/*     */       
/* 206 */       for (int j = 0; j < (this.data[i]).length; j++) {
/*     */         
/* 208 */         returnString = String.valueOf(returnString) + String.format("%10.10f\t", new Object[] { Double.valueOf(this.data[i][j]) });
/*     */       } 
/* 210 */       returnString = String.valueOf(returnString) + "\n";
/*     */     } 
/* 212 */     return returnString;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void show() {
/* 218 */     System.out.println(toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] get() {
/* 225 */     double[][] toget = new double[this.M][this.N];
/* 226 */     for (int i = 0; i < this.M; i++) {
/*     */       
/* 228 */       for (int j = 0; j < this.N; j++) {
/* 229 */         toget[i][j] = this.data[i][j];
/*     */       }
/*     */     } 
/* 232 */     return toget;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Matrix invert(Matrix corrTotal) {
/* 238 */     double[][] first = corrTotal.get();
/* 239 */     double[][] inverted = invert(first);
/* 240 */     return new Matrix(inverted);
/*     */   }
/*     */ 
/*     */   
/*     */   static double[][] invert(double[][] a) {
/* 245 */     int n = a.length;
/* 246 */     double[][] x = new double[n][n];
/* 247 */     double[][] b = new double[n][n];
/* 248 */     int[] index = new int[n]; int i;
/* 249 */     for (i = 0; i < n; i++) {
/* 250 */       b[i][i] = 1.0D;
/*     */     }
/*     */     
/* 253 */     gaussian(a, index);
/*     */ 
/*     */     
/* 256 */     for (i = 0; i < n - 1; i++) {
/* 257 */       for (int j = i + 1; j < n; j++) {
/* 258 */         for (int k = 0; k < n; k++)
/* 259 */           b[index[j]][k] = b[index[j]][k] - a[index[j]][i] * b[index[i]][k]; 
/*     */       } 
/*     */     } 
/* 262 */     for (i = 0; i < n; i++) {
/*     */       
/* 264 */       x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
/* 265 */       for (int j = n - 2; j >= 0; j--) {
/*     */         
/* 267 */         x[j][i] = b[index[j]][i];
/* 268 */         for (int k = j + 1; k < n; k++)
/*     */         {
/* 270 */           x[j][i] = x[j][i] - a[index[j]][k] * x[k][i];
/*     */         }
/* 272 */         x[j][i] = x[j][i] / a[index[j]][j];
/*     */       } 
/*     */     } 
/* 275 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   static void gaussian(double[][] a, int[] index) {
/* 280 */     int n = index.length;
/* 281 */     double[] c = new double[n];
/*     */     
/*     */     int i;
/* 284 */     for (i = 0; i < n; i++) {
/* 285 */       index[i] = i;
/*     */     }
/*     */     
/* 288 */     for (i = 0; i < n; i++) {
/*     */       
/* 290 */       double c1 = 0.0D;
/* 291 */       for (int m = 0; m < n; m++) {
/*     */         
/* 293 */         double c0 = Math.abs(a[i][m]);
/* 294 */         if (c0 > c1)
/* 295 */           c1 = c0; 
/*     */       } 
/* 297 */       c[i] = c1;
/*     */     } 
/*     */ 
/*     */     
/* 301 */     int k = 0;
/* 302 */     for (int j = 0; j < n - 1; j++) {
/*     */       
/* 304 */       double pi1 = 0.0D;
/* 305 */       for (int m = j; m < n; m++) {
/*     */         
/* 307 */         double pi0 = Math.abs(a[index[m]][j]);
/* 308 */         pi0 /= c[index[m]];
/* 309 */         if (pi0 > pi1) {
/*     */           
/* 311 */           pi1 = pi0;
/* 312 */           k = m;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 317 */       int itmp = index[j];
/* 318 */       index[j] = index[k];
/* 319 */       index[k] = itmp;
/* 320 */       for (int i1 = j + 1; i1 < n; i1++) {
/*     */         
/* 322 */         double pj = a[index[i1]][j] / a[index[j]][j];
/*     */ 
/*     */         
/* 325 */         a[index[i1]][j] = pj;
/*     */ 
/*     */         
/* 328 */         for (int l = j + 1; l < n; l++) {
/* 329 */           a[index[i1]][l] = a[index[i1]][l] - pj * a[index[j]][l];
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Matrix normalize(Matrix in) {
/* 336 */     double sum = 0.0D;
/*     */     
/* 338 */     double[][] out = new double[in.data.length][(in.data[0]).length]; byte b; int j;
/*     */     double[][] arrayOfDouble1;
/* 340 */     for (j = (arrayOfDouble1 = in.data).length, b = 0; b < j; ) { double[] arrayOfDouble2 = arrayOfDouble1[b]; byte b1; int k;
/*     */       double[] arrayOfDouble3;
/* 342 */       for (k = (arrayOfDouble3 = arrayOfDouble2).length, b1 = 0; b1 < k; ) { double d = arrayOfDouble3[b1];
/*     */         
/* 344 */         sum += d; b1++; }
/*     */       
/*     */       b++; }
/*     */     
/* 348 */     if (sum == 0.0D)
/*     */     {
/* 350 */       return in;
/*     */     }
/*     */     
/* 353 */     for (int i = 0; i < in.data.length; i++) {
/*     */       
/* 355 */       for (int k = 0; k < (in.data[i]).length; k++)
/*     */       {
/* 357 */         out[i][k] = in.data[i][k] / sum;
/*     */       }
/*     */     } 
/*     */     
/* 361 */     return new Matrix(out);
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Matrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */