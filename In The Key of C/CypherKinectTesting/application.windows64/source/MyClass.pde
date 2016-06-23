public class Joint{                              //This Entire sub class was written by Chris Elcombe and then edited and modified by Nabil Babu
 float sx, sy, sz;
 float dx, dy, dz;
 float px, py, pz;
 float initTime;
 int size;
 int r, g, b, a;
 final int TIMEOUT = 3000; // in millis 
 boolean isDead;
 String shape;
 final int ALPHA_DEC = 5;
 int maxBar;
 
 Joint(float x, float y, float z){
  this.sx = x;
  this.sy = y;
  this.sz = z;
  isDead = false;
  px = x;
  py = y;
  pz = z;
  dx = random(5);
  dy = random(10) - 5;
  dz = 1;
  initTime = millis();
  size = (int) random(30) + 20;
  r = (int) random(255);
  g = (int) random(255);
  b = (int) random(255);
  a = 255;

  maxBar =(((int) (random(5))) + 1) * 20;
  
  int temp = (int) random(3);
  if(temp > 1){
   shape = "rect"; 
  }else{
   shape = "elli"; 
  }
 } 
 
 public void isTimed(){
   if(millis() > initTime + TIMEOUT){
     isDead = true;
   }
 }
 
 public void update(){
   
   if(py + dy * 0.5 <= maxBar){ // hit bar, stop vertical, move constant horizontal
    py = maxBar; 
    px -= 20;
   }else{ // else do normal stuff
   px = px + dx * 0.5;
   py = py + dy * 0.5;
   a = a - ALPHA_DEC;
   }
   
 }
 
 public void drawShape(){
   fill(r, g, b, a);
   stroke(1);
   line(px + size/2, py, px + size/2 + 1, py - 50);
   ellipse(px, py, size, size);
   
 }
 
 public void outOfBounds(){
   if(px + size / 2 < 0){
    isDead = true; 
   }else if(px - size /2 > width){
     isDead = true;
   }else if(py + size / 2 < 0){
     isDead = true; 
   }
 }
 
 public void updateVelocity(float x, float y, float z){
   dx = x - sx + random(30) - 15;
   dy = -1 * abs(y - sy) - 30;
   dz = z - sz;
 }
 
 
}
