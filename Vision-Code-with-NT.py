import cv2
import numpy as np
import time


def nothing(x):
    pass

#frame = cv2.imread('C:/First_Robot/2019VisionImages/RocketPanelStraight48in.jpg');    #the location of the image
#frame = np.zeros((300,512,3), np.uint8)
cap = cv2.VideoCapture("http://10.49.39.93/mjpg/video.mjpg")
#cap = cv2.VideoCapture(0)
if cap == 0:
      print("NOT WORKING")

font = cv2.FONT_HERSHEY_TRIPLEX

cv2.namedWindow("Trackbars")

cv2.createTrackbar("L - H", "Trackbars", 0, 179, nothing)

#distanceToCam = 150
#actualWidth = 14
#actualLength = 5
#percievedFocalLength = 3000

while True:
      ret, frame = cap.read()
#      greyFrame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
#      blurred_frame = cv2.GaussianBlur(frame, (5, 5), 0)
      hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
      kernel = np.ones((5, 5), np.uint8)
      
      l_h = 22 #100
      l_s = 80  #134
      l_v = 204 #4
      u_h = 179 #179
      u_s = 255 #255
      u_v = 255 #255
      
      lower_blue = np.array([l_h, l_s, l_v])
      upper_blue = np.array([u_h, u_s, u_v])
      mask = cv2.inRange(hsv, lower_blue, upper_blue)

      
      contours,hierachy=cv2.findContours(mask,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
      
            
      for cnt in contours:
        area = cv2.contourArea(cnt)
        approx = cv2.approxPolyDP(cnt, 0.02*cv2.arcLength(cnt, True), True)
        x = approx.ravel()[0]
        y = approx.ravel()[1]
 
        if area > 300:
            rect = cv2.minAreaRect(cnt)
            box = cv2.boxPoints(rect)
            box = np.int0(box)
            cv2.drawContours(mask,[box],0,(0,0,255),2)
 
            if len(approx) == 4:
                cv2.putText(frame, "Rectangle", (x, y), font, 1, (0, 0, 0))

                cv2.putText(frame, str(cv2.arcLength(cnt, True)), (10, 50), font, 1, (0, 0, 0))
                
                perim = cv2.arcLength(cnt, True)
                rectArea = cv2.contourArea(cnt)
                #convert pixels to mm
                f = 3.5 #mm
        
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
             
                m = ((m_x + m_y)/f)/2
#                m = (m_x + m_y)/f        
                x = pr_x / m
        
            #image size in pixels
                imgsizepx = (perim / 13.4) * 2.8 #get this value from contour program 
         
                object_real_world_mm = 140
                object_image_sensor_mm = imgsizepx / x
                image_sensor_height_mm = 6.35 
        
              #distance_mm = (object_real_world_mm * f * cr_y)/ (pr_y * image_sensor_height_mm)
        
                distance_mm = (f * object_real_world_mm * pr_y) / (imgsizepx * image_sensor_height_mm)
        
                distance_in = distance_mm / 25.4
        
                distance_real = 2.1 * distance_in #
        
                print(distance_real) 
                
                if distance_real == 21:
                      break
                        
            

      kernel = np.ones((5, 5), np.uint8)

      
      cv2.imshow("Trackbars", frame)
      
      key = cv2.waitKey(1)
      if cv2.waitKey(1) & 0xFF == 27 or ret==False :
        break
      time.sleep(3)
    

cv2.destroyAllWindows()

