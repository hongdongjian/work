//
//  Public.h
//  SalesManagement
//
//  Created by 洪东楗 on 2017/4/24.
//  Copyright © 2017年 洪东楗. All rights reserved.
//

#ifndef Public_h
#define Public_h

#define screen_width [UIScreen mainScreen].bounds.size.width
#define screen_height [UIScreen mainScreen].bounds.size.height
#define RGB(a,b,c) [UIColor colorWithRed:a/255.0 green:b/255.0 blue:c/255.0 alpha:1]
#define musicBackgroundColor RGB(255,240,245)
#define mainLeftWidth 100
#define salesDetailLeftWidth 350
#define dataDetailLeftWidth 350
#define dataRightWidth (screen_width - mainLeftWidth - dataDetailLeftWidth)
#define detailTopHeight 20
#endif /* Public_h */
