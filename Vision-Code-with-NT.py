import cv2
from networktables import NetworkTables
import numpy as np

cond = threading.Condition()
notified = [False]

def connectionListener(connected, info):
    print(info , '; Connected=%s' %connected)
    with cond:
        notified[0] = True
        cond.notify()

with cond:
    print("Waiting")
    if not notified[0]:
        cond.wait()

print("Connected")
sd = NetworkTables.getTable('SmartDashboard')

NetworkTables.initialize(server='10.49.39.2')
NetworkTables.addConnectionListener(connectionListener, immediateNotify=True)

def nothing(x):
    pass

#frame = cv2.imread('C:/First_Robot/2019VisionImages/RocketPanelStraight48in.jpg');    #the location of the image
#frame = np.zeros((300,512,3), np.uint8)
cap = cv2.VideoCapture("http://10.49.39.93/mjpg/video.mjpg")
#cap = cv2.VideoCapture("http://169.254.239.162/mjpg/video.mjpg")
if cap == 0:
      print("NOT WORKING")

font = cv2.FONT_HERSHEY_TRIPLEX
#distanceToCam = 150
#actualWidth = 14
#actualLength = 5
#percievedFocalLength = 3000

while True:
    isDetected = false
      ret, frame = cap.read()
       kernel = np.ones((5, 5), np.uint8)
#      greyFrame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
#      blurred_frame = cv2.GaussianBlur(frame, (5, 5), 0)
      hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
      kernel = np.ones((5, 5), np.uint8)
      
      l_h = 100 #100
      l_s = 65  #134
      l_v = 186 #4
      u_h = 176 #179
      u_s = 236 #255
      u_v = 255 #255
      
      lower_blue = np.array([l_h, l_s, l_v])
      upper_blue = np.array([u_h, u_s, u_v])
      mask = cv2.inRange(hsv, lower_blue, upper_blue)

      
      _, contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)
      
# find contours
# draw contour
# send cordinates of the contour 

            
      for cnt in contours:
        area = cv2.contourArea(cnt)
        approx = cv2.approxPolyDP(cnt, 0.02*cv2.arcLength(cnt, True), True)
        x = approx.ravel()[0]
        y = approx.ravel()[1]
 
        if area > 400:
            rect = cv2.minAreaRect(cnt)
            box = cv2.boxPoints(rect)
            box = np.int0(box)
            cv2.drawContours(img,[box],0,(0,0,255),2)
            isdetected = true
 
            if len(approx) == 4:
                cv2.putText(frame, "Rectangle", (x, y), font, 1, (0, 0, 0))
                cv2.putText(frame, str(cv2.arcLength(cnt, True)), (10, 50), font, 1, (0, 0, 0))
                sd.putNumber('X',x)
                sd.putNumber('Y',y)
                sd.putBoolean('tapeFound', True)
                perim = cv2.arcLength(cnt, True)
                rectArea = cv2.contourArea(cnt)
                #convert pixels to mm
                f = 3.5 #mm
            else:
                sd.putNumber('X',-1)
                sd.putNumber('Y',-1)
                sd.putBoolean('tapeFound', False)

        
          #camera resolution px
                cr_x = 800
                cr_y = 600
        
        #photo resolution px
                pr_x = 800
                pr_y = 600
        
        #m_x and m_y must be obtained by calibrating camera.
                m_x = 714
                m_y = 713
        
         
                f_x = f * m_x 
           
                f_y = f * m_y
             
                m = ((m_x + m_y)/2)/f
         
                x = pr_x / m
        
            #image size in pixels
                imgsizepx = (perim / 7.6) * 2.8 #get this value from contour program 
         
                object_real_world_mm = 140
                object_image_sensor_mm = imgsizepx / x
                image_sensor_height_mm = 6.35 
        
              #distance_mm = (object_real_world_mm * f * cr_y)/ (pr_y * image_sensor_height_mm)
        
                distance_mm = (f * object_real_world_mm * pr_y) / (imgsizepx * image_sensor_height_mm)
        
                distance_in = distance_mm / 25.4
        
                distance_real = 2.1 * distance_in #
        
                print(distance_real) 
                        
            


      
      
      key = cv2.waitKey(1)
      if cv2.waitKey(1) & 0xFF == 27 or ret==False:
        break


cv2.destroyAllWindows()
