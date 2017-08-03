//
//  MyKeyBoard.m
//  test
//
//  Created by 洪东楗 on 2017/4/29.
//  Copyright © 2017年 洪东楗. All rights reserved.
//

#import "MyKeyBoard.h"
#import "Public.h"

@interface MyKeyBoard()
{
    //键盘两边空白
    CGFloat bank;
    //字母键间的空白
    CGFloat enBank;
    //数字键间的空白
    CGFloat numBank;
    //字母盘和数字盘间的空白
    CGFloat midBank;
    //字母键的宽度
    CGFloat enBtnWidth;
}
@property (nonatomic, weak) UITextField *responder;
@property (nonatomic, strong) NSMutableArray *buttonArray;
@end
@implementation MyKeyBoard

//得到让键盘响应的textfield
- (UITextField *)responder {
    
    UIWindow *keyWindow = [[UIApplication sharedApplication] keyWindow];
    UIView *firstResponder = [keyWindow valueForKey:@"firstResponder"];
    _responder = (UITextField *)firstResponder;
   
    return _responder;
}

//键盘初始化
- (instancetype)init {
    self = [super init];
    if (self) {
        self.backgroundColor = RGB(222, 222, 222);
        
        self.buttonArray = [[NSMutableArray alloc] init];
        
        bank = 8;
        enBank = 10;
        numBank = 4;
        midBank = 15;
        enBtnWidth = (screen_width - bank*2 - enBank*9 - numBank*3 - midBank) / (10 + 4*1.5);
        self.frame = CGRectMake(0, 0, screen_width, 20 + (enBtnWidth + 10) * 4 + 30);
        
        UIButton *btn = [self getKeyBoardButton:@"q"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"w"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"e"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"r"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"t"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"y"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"u"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"i"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"o"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"p"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"a"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"s"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"d"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"f"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"g"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"h"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"j"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"k"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"l"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"z"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"x"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"c"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"v"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"b"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"n"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"m"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"7"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"8"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"9"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"✖︎"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"4"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"5"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"6"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"清除所有"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"1"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"2"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"3"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"换行"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"-"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"0"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"."];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"隐藏"];
        [self.buttonArray addObject:btn];
        btn = [self getKeyBoardButton:@"小写"];
        btn.tag = 4;
        [self.buttonArray addObject:btn];
        for (int i = 0; i < self.buttonArray.count; i++) {
            UIButton *btn = [self.buttonArray objectAtIndex:i];
            btn.tag = i;
        }
    }
    return self;
}

//设计布局
- (void)layoutSubviews {
    [super layoutSubviews];
    CGFloat numBtnWidth = enBtnWidth*1.5;
    
    for (int i = 0; i < 10 ; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = bank + (enBtnWidth + enBank) * i;
        btn.frame = CGRectMake(x, 20, enBtnWidth, enBtnWidth);
    }
    
    for (int i = 26; i < 30; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = bank + (enBtnWidth * 10 + enBank * 9) + (numBtnWidth + numBank) * (i - 26) + midBank;
        btn.frame = CGRectMake(x, 20, numBtnWidth, enBtnWidth);
    }
    for (int i = 30; i < 34; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = bank + (enBtnWidth * 10 + enBank * 9) + (numBtnWidth + numBank) * (i - 30) + midBank;
        CGFloat y = enBtnWidth + 20 + 10;
        btn.frame = CGRectMake(x, y, numBtnWidth, enBtnWidth);
    }
    for (int i = 34; i < 38; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = bank + (enBtnWidth * 10 + enBank * 9) + (numBtnWidth + numBank) * (i - 34) + midBank;
        CGFloat y = (enBtnWidth + 10) * 2 + 20 ;
        btn.frame = CGRectMake(x, y, numBtnWidth, enBtnWidth);
    }
    for (int i = 38; i < 42; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = bank + (enBtnWidth * 10 + enBank * 9) + (numBtnWidth + numBank) * (i - 38) + midBank;
        CGFloat y = (enBtnWidth + 10) * 3 + 20 ;
        btn.frame = CGRectMake(x, y, numBtnWidth, enBtnWidth);
    }
    
    CGFloat height = (enBtnWidth * 4 + 10 * 3);
    CGFloat midHeight = (height - enBtnWidth * 3) / 2;
    CGFloat midLeftBank = (enBtnWidth + enBank) / 2 + bank;
    CGFloat y = 20 + enBtnWidth + midHeight;
    for (int i = 10; i < 19 ; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = midLeftBank + (enBtnWidth + enBank) * (i - 10);
        btn.frame = CGRectMake(x, y, enBtnWidth, enBtnWidth);
    }
    
    y += midHeight + enBtnWidth;
    for (int i = 19; i < 26 ; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        CGFloat x = midLeftBank + (enBtnWidth + enBank) * (i - 19);
        btn.frame = CGRectMake(x, y, enBtnWidth, enBtnWidth);
    }
    
    CGFloat x = midLeftBank + (enBtnWidth + enBank) * (26 - 19);
    UIButton *btn = [self.buttonArray lastObject];
    btn.frame = CGRectMake(x, y, enBtnWidth*2 + enBank, enBtnWidth);
}

//获得自定义按钮
- (UIButton *)getKeyBoardButton:(NSString *)titleName {
    UIButton *btn = [[UIButton alloc] init];
    btn.backgroundColor = [UIColor whiteColor];
    [btn setTitle:titleName forState:UIControlStateNormal];
    [btn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    btn.layer.shadowOffset =  CGSizeMake(0.5, 0.5);
    btn.layer.shadowOpacity = 0.5;
    btn.layer.shadowColor =  [UIColor blackColor].CGColor;
    [btn addTarget:self action:@selector(keyBoardClick:) forControlEvents:UIControlEventTouchUpInside];
    btn.layer.cornerRadius = 5;
    btn.titleLabel.font = [UIFont boldSystemFontOfSize:20];
    [self addSubview:btn];
    return btn;
}

//键盘响应事件
- (void)keyBoardClick:(UIButton *)center{
    //NSLog(@"%ld", center.tag);
    if (center.tag == 29) {
        //删除
        if (self.responder.text.length) {
            self.responder.text = [self.responder.text substringToIndex:self.responder.text.length-1];
        }
    } else if (center.tag == 33) {
        //清空
        self.responder.text = @"";
    } else if (center.tag == 37) {
        //退出
        [[UIApplication sharedApplication].keyWindow endEditing:YES];
    } else if (center.tag == 41) {
        //退出
        [[UIApplication sharedApplication].keyWindow endEditing:YES];
    } else if (center.tag == 42) {
        //设置大小写
        UIButton *btn = (UIButton *)[self viewWithTag:42];
        if ([btn.titleLabel.text isEqualToString:@"小写"]) {
            [btn setTitle:@"大写" forState:UIControlStateNormal];
        } else if ([center.titleLabel.text isEqualToString:@"大写"]) {
            [btn setTitle:@"小写" forState:UIControlStateNormal];
        }
        [self switchSmallAndBig];
    } else {
        //将点击的数字和字母加入textField
        self.responder.text = [self.responder.text stringByAppendingString:center.titleLabel.text];
    }
}

//转换字母
- (void)switchSmallAndBig {
    for (int i = 0; i < 26; i++) {
        UIButton *btn = [self.buttonArray objectAtIndex:i];
        char ch = [btn.titleLabel.text characterAtIndex:0];
        if (ch >= 'a' && ch <= 'z') {
            ch = ch - 'a' + 'A';
            [btn setTitle:[NSString stringWithFormat:@"%c", ch] forState:UIControlStateNormal];
        } else if (ch >= 'A' && ch <= 'Z') {
            ch = ch - 'A' + 'a';
            [btn setTitle:[NSString stringWithFormat:@"%c", ch] forState:UIControlStateNormal];
        }
    }
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
