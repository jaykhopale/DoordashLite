# Doordash lite Android app challenge
**1. Focus Areas**    
I have heavily focused on Architecture, data flow and testability with modern Android Architecture 
Components for this challenge. The second area of my focus has been to follow Material Design UI 
Guidelines for Lists as mentioned here 
[https://material.io/components/lists](https://material.io/components/lists)

**2. Copied-in code or copied-in dependencies**  
Copied in code has been used in unit tests for `LiveDataTestUtil.kt` from 
[https://github.com/android/architecture-components-samples/blob/master/LiveDataSample/app/src/test/java/com/android/example/livedatabuilder/util/LiveDataTestUtil.kt](https://github.com/android/architecture-components-samples/blob/master/LiveDataSample/app/src/test/java/com/android/example/livedatabuilder/util/LiveDataTestUtil.kt).

**3. Tablet / phone focus**  
I have focused primarily on Phones while building this. However, the app does support Landscape orientation.
Every effort has been made to ensure that change in orientation doesn't trigger an additional network
request and the state is maintained. 
