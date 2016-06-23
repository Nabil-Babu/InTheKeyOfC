import pKinect.PKinect;
import pKinect.SkeletonData;
import arb.soundcipher.*;



PKinect kinect;                                                                               //Chris programmed the following code while parts of it were revised by Nabil Babu....{
ArrayList<SkeletonData> bodies;                  
int[] ValidJoints = {3, 6, 9, 12, 14, 16, 18};  
Visualizer vis;                                 
int counter = 0;                                                            
void setup()                                    
{                                                  
  size(1920, 1080);                                  
  background(255);                              
  frameRate(24);
  kinect = new PKinect(this);
  bodies = new ArrayList<SkeletonData>();
  smooth();
  vis = new Visualizer(); 
}

void draw()
{
  if(bodies.size() == 0){
    vis.update(-100, 0, -10, 3, 0);
    vis.update(-100, 0, -10, 6, 0);
    vis.update(-100, 0, -10, 9, 0);
    vis.update(-100, 0, -10, 12, 0);
    vis.update(-100, 0, -10, 16, 0);
    vis.update(-100, 0, -10, 14, 0);
    vis.update(-100, 0, -10, 18, 0);
    
    
  }
  
  if(bodies.size() > 0){
   
    float headx = map(bodies.get(0).skeletonPositions[3].x, 0, 1, width/4, width*3/4); 
    float heady = map(bodies.get(0).skeletonPositions[3].y, 0, 1, height/2, height);
    float headz = map(bodies.get(0).skeletonPositions[3].z, -1, 1, 50, 0);

    float lwristx = map(bodies.get(0).skeletonPositions[6].x, 0, 1, width/4, width*3/4); 
    float lwristy = map(bodies.get(0).skeletonPositions[6].y, 0, 1, height/2, height);
    float lwristz = map(bodies.get(0).skeletonPositions[6].z, -1, 1, 50, 0);
    
    float rwristx = map(bodies.get(0).skeletonPositions[9].x, 0, 1, width/4, width*3/4); 
    float rwristy = map(bodies.get(0).skeletonPositions[9].y, 0, 1, height/2, height);
    float rwristz = map(bodies.get(0).skeletonPositions[9].z, -1, 1, 50, width/4);
    
    float lkneex = map(bodies.get(0).skeletonPositions[12].x, 0, 1, width/4, width*3/4); 
    float lkneey = map(bodies.get(0).skeletonPositions[12].y, 0, 1, height/2, height); 
    float lkneez = map(bodies.get(0).skeletonPositions[12].z, -1, 1, 50, 0);
    
    float rkneex = map(bodies.get(0).skeletonPositions[16].x, 0, 1, width/4, width*3/4); 
    float rkneey = map(bodies.get(0).skeletonPositions[16].y, 0, 1, height/2, height); 
    float rkneez = map(bodies.get(0).skeletonPositions[16].z, -1, 1, 50, 0);
    
    float lfootx = map(bodies.get(0).skeletonPositions[14].x, 0, 1, width/4, width*3/4); 
    float lfooty = map(bodies.get(0).skeletonPositions[14].y, 0, 1, height/2, height); 
    float lfootz = map(bodies.get(0).skeletonPositions[14].z, -1, 1, 50, 0);
    
    float rfootx = map(bodies.get(0).skeletonPositions[18].x, 0, 1, width/4, width*3/4); 
    float rfooty = map(bodies.get(0).skeletonPositions[18].y, 0, 1, height/2, height);
    float rfootz = map(bodies.get(0).skeletonPositions[18].z, -1, 1, 50, 0);              //..... }
    
    vis.update(headx, heady, headz, 3, bodies.size());                                    // Nabil Babu wrote the following code.... {
    vis.update(lwristx, lwristy, lwristz, 6, bodies.size());
    vis.update(rwristx, rwristy, rwristz, 9, bodies.size());
    vis.update(lkneex, lkneey, lkneez, 12, bodies.size());
    vis.update(rkneex, rkneey, rkneez, 16, bodies.size());
    vis.update(lfootx, lfooty, lfootz, 14, bodies.size());
    vis.update(rfootx, rfooty, rfootz, 18, bodies.size());                                // .......}  
    
    if(counter % 65 == 0){                                                                // Chris Elcombe wrote the following code..... {
      if(vis.isMoving()){
      vis.makeNote();
      }
    }
    
    counter++;
  }                                                                                      // .....}
  
  
 background(255);                                                                        // All the code below were provided by the Creators of the PKinect Library 
                                                                                         // But modified and edited by Chris Elcombe 
  vis.draw();
  for (int i=0; i<bodies.size(); i++) 
  {
    drawSkeleton(bodies.get(i));
    drawPosition(bodies.get(i));
  }
}

void drawPosition(SkeletonData _s) 
{
  noStroke();
  fill(0, 100, 255);
}

void drawSkeleton(SkeletonData _s) 
{
  // Body
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HEAD, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SPINE, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);

  // Left Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HAND_LEFT);

  // Right Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_HAND_RIGHT);

  // Left Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_LEFT);

  // Right Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_RIGHT);
}

void DrawBone(SkeletonData _s, int _j1, int _j2) 
{
  noFill();
  stroke(0, 0, 255);
  if (_s.skeletonPositionTrackingState[_j1] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED &&
    _s.skeletonPositionTrackingState[_j2] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED) {
    line(_s.skeletonPositions[_j1].x*width/2 + width/4, 
    _s.skeletonPositions[_j1].y*height/2+height/2, 
    _s.skeletonPositions[_j2].x*width/2 + width/4, 
    _s.skeletonPositions[_j2].y*height/2+height/2);
  }
}

void appearEvent(SkeletonData _s) 
{
  if (_s.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
  {
    return;
  }
  synchronized(bodies) {
   
       bodies.add(0 ,_s);
  }
}

void disappearEvent(SkeletonData _s) 
{
  synchronized(bodies) {
    for (int i=bodies.size()-1; i>=0; i--) 
    {
      if (_s.dwTrackingID == bodies.get(i).dwTrackingID) 
      {
       bodies.remove(i);
       bodies = new ArrayList<SkeletonData>();
       bodies.clear();
      }
    }
  }
}

void moveEvent(SkeletonData _b, SkeletonData _a) 
{
  if (_a.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
  {
    return;
  }
  synchronized(bodies) {
    for (int i=bodies.size()-1; i>=0; i--) 
    {
      if (_b.dwTrackingID == bodies.get(i).dwTrackingID) 
      {
        bodies.get(i).copy(_a);
        break;
      }
    }
  }
}
