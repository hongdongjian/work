//
//  ViewController.m
//  test
//
//  Created by 洪东楗 on 2017/4/29.
//  Copyright © 2017年 洪东楗. All rights reserved.
//

#import "ViewController.h"
#import "MyKeyBoard.h"
#import "Public.h"

@interface ViewController () <UITextFieldDelegate>
@property (nonatomic, strong) UITextField *text;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.text = [[UITextField alloc] initWithFrame:CGRectMake(30, 500, 200, 30)];
    self.text.borderStyle = UITextBorderStyleRoundedRect;
    self.text.delegate = self;
    [self.view addSubview:self.text];
    self.text.inputView = [[MyKeyBoard alloc] init];
    
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(400, 30, 100, 30)];
    label.backgroundColor = [UIColor redColor];
    [self.view addSubview:label];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void) keyboardWillShow:(NSNotification *)notification {
    //获取键盘高度，在不同设备上，以及中英文下是不同的
    CGFloat kbHeight = [[notification.userInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue].size.height;
    
    //计算出键盘顶端到inputTextView panel底端的距离(加上自定义的缓冲距离INTERVAL_KEYBOARD)
    CGFloat offset = (self.text.frame.origin.y+self.text.frame.size.height+30) - (self.view.frame.size.height - kbHeight);
    
    // 取得键盘的动画时间，这样可以在视图上移的时候更连贯
    double duration = [[notification.userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey] doubleValue];
    
    //将视图上移计算好的偏移
    if(offset > 0) {
        [UIView animateWithDuration:duration animations:^{
            self.view.frame = CGRectMake(0.0f, -offset, self.view.frame.size.width, self.view.frame.size.height);
        }];
    }
}

///键盘消失事件
- (void) keyboardWillHide:(NSNotification *)notify {
    // 键盘动画时间
    double duration = [[notify.userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey] doubleValue];
    
    //视图下沉恢复原状
    [UIView animateWithDuration:duration animations:^{
        self.view.frame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height);
    }];
}

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event{
    [self.text resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [textField resignFirstResponder];
    return YES;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
