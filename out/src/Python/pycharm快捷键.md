多行注释 ctrl+/
删除一行 ctrl+del

moudle_name:是对应的模块名；请自行更换为自己需要更新的模块名

    Tab:右移
    
    Shift+Tab:左移

1. 查看所有可更新的模块：　　pip list --outdated
2. 更新某一个模块：　　　　　pip install --upgrade module_name
3. 指定更新源更新模块　　　　pip install --upgrade -i https://pypi.douban.com/simple moudle_name
4. 安装对应的模块：　　　　　pip install pip-review
5. 更新所有的模块： 　　　　  pip-review --local --interactive
6. 更新库到执行版本pip3 install --upgrade tensorflow==1.1.0rc2

