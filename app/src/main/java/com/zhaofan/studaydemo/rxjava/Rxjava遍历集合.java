package com.zhaofan.studaydemo.rxjava;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/28
 * description:使用rxjava遍历集合
 * <p>
 * <p>
 * RxJava 有四个基本概念：Observable (可观察者，即被观察者)、
 * Observer (观察者)、
 * subscribe (订阅)、事件。
 * Observable 和Observer 通过 subscribe() 方法实现订阅关系，
 * 从而 Observable 可以在需要的时候发出事件来通知 Observer。
 * <p>
 * <p>
 * onCompleted(): 事件队列完结。RxJava 不仅把每个事件单独处理，还会把它们看做一个队列。RxJava 规定，当不会再有新的onNext() 发出时，需要触发 onCompleted() 方法作为标志。
 * onError(): 事件队列异常。在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出。
 * 在一个正确运行的事件序列中, onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。需要注意的是，onCompleted() 和 onError() 二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。
 * <p>
 * <p>
 * <p>
 * Observer 即观察者，它决定事件触发的时候将有怎样的行为。 RxJava 中的 Observer 接口的实现方式：
 * <p>
 * 除了 Observer 接口之外，RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber。 Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的：
 * <p>
 * <p>
 * <p>
 * <p>
 * onStart(): 这是 Subscriber 增加的方法。它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，例如数据的清零或重置。
 * 这是一个可选方法，默认情况下它的实现为空。需要注意的是，如果对准备工作的线程有要求（例如弹出一个显示进度的对话框，这必须在主线程执行），
 * onStart() 就不适用了，因为它总是在 subscribe 所发生的线程被调用，而不能指定线程。要在指定的线程来做准备工作，可以使用 doOnSubscribe() 方法，
 * 具体可以在后面的文中看到。
 * unsubscribe(): 这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅。在这个方法被调用后，Subscriber将不再接收事件。
 * 一般在这个方法调用前，可以使用 isUnsubscribed() 先判断一下状态。 unsubscribe() 这个方法很重要，因为在subscribe() 之后，
 * Observable 会持有 Subscriber 的引用，这个引用如果不能及时被释放，将有内存泄露的风险。
 * 所以最好保持一个原则：要在不再使用的时候尽快在合适的地方（例如 onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，
 * 以避免内存泄露的发生。
 * <p>
 * 2) 创建 Observable
 * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。 RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则：
 * <p>
 * 可以看到，这里传入了一个 OnSubscribe 对象作为参数。OnSubscribe 会被存储在返回的 Observable 对象中，它的作用相当于一个计划表，当 Observable 被订阅的时候，
 * OnSubscribe 的 call() 方法会自动被调用，事件序列就会依照设定依次触发（对于上面的代码，就是观察者Subscriber 将会被调用三次 onNext() 和一次 onCompleted()）。
 * 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
 * <p>
 * create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列
 * <p>
 * <p>
 * 3) Subscribe (订阅)
 * <p>
 * 创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。代码形式很简单：
 * <p>
 * subscriber() 做了3件事：
 * <p>
 * 调用 Subscriber.onStart() 。这个方法在前面已经介绍过，是一个可选的准备方法。
 * 调用 Observable 中的 OnSubscribe.call(Subscriber) 。在这里，事件发送的逻辑开始运行。从这也可以看出，在 RxJava 中，Observable
 * 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
 * 将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe().
 * <p>
 * 简单解释一下这段代码中出现的 Action1 和 Action0。 Action0 是 RxJava 的一个接口，它只有一个方法 call()，这个方法是无参无返回值的；
 * 由于 onCompleted() 方法也是无参无返回值的，因此 Action0 可以被当成一个包装对象，将 onCompleted() 的内容打包起来将自己作为一个参数传入 subscribe()
 * 以实现不完整定义的回调。这样其实也可以看做将 onCompleted() 方法作为参数传进了subscribe()，相当于其他某些语言中的『闭包』。
 * Action1 也是一个接口，它同样只有一个方法 call(T param)，这个方法也无返回值，但有一个参数；
 * 与 Action0 同理，由于 onNext(T obj) 和 onError(Throwable error) 也是单参数无返回值的，因此 Action1 可以将 onNext(obj) 和 onError(error)
 * 打包起来传入 subscribe() 以实现不完整定义的回调。事实上，虽然 Action0 和 Action1 在 API 中使用最广泛，但 RxJava 是提供了多个 ActionX 形式的接口
 * (例如 Action2, Action3) 的，它们可以被用以包装不同的无返回值的方法。
 * <p>
 * <p>
 * <p>
 * 3. 线程控制 —— Scheduler (一)
 * <p>
 * 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。
 * 如果需要切换线程，就需要用到 Scheduler （调度器）。
 * <p>
 * 1) Scheduler 的 API (一)
 * <p>
 * 在RxJava 中，Scheduler ——调度器，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。RxJava 已经内置了几个 Scheduler ，
 * 它们已经适合大多数的使用场景：
 * <p>
 * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
 * Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
 * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io()
 * 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
 * Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
 * 这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
 * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
 * 有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。
 * <p>
 * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
 * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
 * <p>
 * <p>
 * 2) Scheduler 的原理 (一)
 * <p>
 * RxJava 的 Scheduler API 很方便，也很神奇（加了一句话就把线程切换了，怎么做到的？而且 subscribe() 不是最外层直接调用的方法吗，它竟然也能被指定线程？）。
 * 然而 Scheduler 的原理需要放在后面讲，因为它的原理是以下一节《变换》的原理作为基础的。
 * <p>
 * 好吧这一节其实我屁也没说，只是为了让你安心，让你知道我不是忘了讲原理，而是把它放在了更合适的地方。
 * <p>
 * 4. 变换
 * <p>
 * 终于要到牛逼的地方了，不管你激动不激动，反正我是激动了。
 * <p>
 * RxJava 提供了对事件序列进行变换的支持，这是它的核心功能之一，也是大多数人说『RxJava 真是太好用了』的最大原因。所谓变换，
 * 就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。概念说着总是模糊难懂的，来看 API。
 * <p>
 * <p>
 * map(): 事件对象的直接变换，具体功能上面已经介绍过。它是 RxJava 最常用的变换。 map() 的示意图：
 * <p>
 * flatMap(): 这是一个很有用但非常难理解的变换，因此我决定花多些篇幅来介绍它。 首先假设这么一种需求：假设有一个数据结构『学生』，
 * 现在需要打印出一组学生的名字。实现方式很简单：
 * <p>
 * 依然很简单。那么如果我不想在 Subscriber 中使用 for 循环，而是希望 Subscriber 中直接传入单个的 Course 对象呢（这对于代码复用很重要）？
 * 用 map() 显然是不行的，因为 map() 是一对一的转化，而我现在的要求是一对多的转化。那怎么才能把一个 Student 转化成多个 Course 呢？
 * <p>
 * 这个时候，就需要用 flatMap() 了：
 * 从上面的代码可以看出， flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map() 不同的是， flatMap() 中返回的是个
 * Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。flatMap() 的原理是这样的：
 * 1. 使用传入的事件对象创建一个 Observable 对象；
 * 2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
 * 3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
 * 这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
 * <p>
 * <p>
 * 2) 变换的原理：lift()
 * <p>
 * 这些变换虽然功能各有不同，但实质上都是针对事件序列的处理和再发送。而在 RxJava 的内部，它们是基于同一个基础的变换方法：lift(Operator)。
 * 首先看一下 lift() 的内部实现（仅核心代码）：
 * <p>
 * subscribe() 中这句话的 onSubscribe 指的是 Observable 中的 onSubscribe 对象，这个没有问题，但是 lift() 之后的情况就复杂了点。
 * 当含有 lift() 时：
 * 1.lift() 创建了一个 Observable 后，加上之前的原始 Observable，已经有两个 Observable 了；
 * 2.而同样地，新 Observable 里的新 OnSubscribe 加上之前的原始 Observable 中的原始 OnSubscribe，也就有了两个 OnSubscribe；
 * 3.当用户调用经过 lift() 后的 Observable 的 subscribe() 的时候，使用的是 lift() 所返回的新的 Observable ，
 * 于是它所触发的onSubscribe.call(subscriber)，也是用的新 Observable 中的新 OnSubscribe，即在 lift() 中生成的那个 OnSubscribe；
 * 4.而这个新 OnSubscribe 的 call() 方法中的 onSubscribe ，就是指的原始 Observable 中的原始 OnSubscribe ，在这个 call() 方法里，
 * 新 OnSubscribe 利用 operator.call(subscriber) 生成了一个新的 Subscriber（Operator 就是在这里，通过自己的 call()方法将新 Subscriber
 * 和原始 Subscriber 进行关联，并插入自己的『变换』代码以实现变换），然后利用这个新 Subscriber 向原始Observable 进行订阅。
 * 这样就实现了 lift() 过程，有点像一种代理机制，通过事件拦截和处理实现事件序列的变换。
 * <p>
 * 3) compose: 对 Observable 整体的变换
 * <p>
 * 除了 lift() 之外， Observable 还有一个变换方法叫做 compose(Transformer)。它和 lift() 的区别在于，
 * lift() 是针对事件项和事件序列的，而 compose() 是针对 Observable 自身进行变换。
 * 举个例子，假设在程序中有多个 Observable ，并且他们都需要应用一组相同的 lift() 变换。
 * <p>
 * 5. 线程控制：Scheduler (二)
 * <p>
 * 除了灵活的变换，RxJava 另一个牛逼的地方，就是线程的自由控制。
 * <p>
 * 1) Scheduler 的 API (二)
 * <p>
 * 前面讲到了，可以利用 subscribeOn() 结合 observeOn() 来实现线程控制，让事件的产生和消费发生在不同的线程。可是在了解了map() flatMap() 等变换方法后，
 * 有些好事的（其实就是当初刚接触 RxJava 时的我）就问了：能不能多切换几次线程？
 * <p>
 * 答案是：能。因为 observeOn() 指定的是 Subscriber 的线程，而这个 Subscriber 并不是（严格说应该为『不一定是』，但这里不妨理解为『不是』）subscribe()
 * 参数中的 Subscriber ，而是 observeOn() 执行时的当前 Observable 所对应的 Subscriber ，即它的直接下级 Subscriber 。换句话说，observeOn()
 * 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可。
 * <p>
 * 图中共有 5 处含有对事件的操作。由图中可以看出，①和②两处受第一个 subscribeOn() 影响，运行在红色线程；③和④处受第一个observeOn() 的影响，
 * 运行在绿色线程；⑤处受第二个 onserveOn() 影响，运行在紫色线程；而第二个 subscribeOn() ，由于在通知过程中线程就被第一个 subscribeOn() 截断，
 * 因此对整个流程并没有任何影响。这里也就回答了前面的问题：当使用了多个subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。
 * <p>
 * 3) 延伸：doOnSubscribe()
 * <p>
 * 然而，虽然超过一个的 subscribeOn() 对事件处理的流程没有影响，但在流程之前却是可以利用的。
 * <p>
 * 在前面讲 Subscriber 的时候，提到过 Subscriber 的 onStart() 可以用作流程开始前的初始化。
 * 然而 onStart() 由于在 subscribe()发生时就被调用了，因此不能指定线程，而是只能执行在 subscribe() 被调用时的线程。
 * 这就导致如果 onStart() 中含有对线程有要求的代码（例如在界面上显示一个 ProgressBar，这必须在主线程执行），将会有线程非法的风险，
 * 因为有时你无法预测 subscribe() 将会在什么线程执行。
 * <p>
 * 而与 Subscriber.onStart() 相对应的，有一个方法 Observable.doOnSubscribe() 。它和 Subscriber.onStart() 同样是在subscribe()
 * 调用后而且在事件发送前执行，但区别在于它可以指定线程。默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；
 * 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
 */
public class Rxjava遍历集合 {
    //################ 使用java线程遍历集合 #############
    public void javaInterator(Hotel hotel, Activity activity) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //从服务器端获取酒店列表
                List<Room> rooms = hotel.getRooms();
                for (Room room : rooms) {
                    if (room.getPrice() > 500) {
                        //使用java切换到主线程
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                displayUI(room);
                            }
                        });
                    }
                }
            }
        }.start();
    }

    private void displayUI(Room room) {
    }


    //############## 使用rxJava遍历集合 ##################
    //操作符 flatMap flatMap 通过hotel转换程room并有返回值 Room
    //操作符 filter 过滤操作符 通过过滤塞选出我们想要的数据

    public void rxjavaInterator(Iterable<Hotel> hotel) {
        Observable.fromIterable(hotel)
                .flatMap(new Function<Hotel, ObservableSource<Room>>() {
                    @Override
                    public ObservableSource<Room> apply(Hotel hotel) throws Exception {
                        return Observable.fromIterable(hotel.getRooms());
                    }
                })
                .filter(new Predicate<Room>() {
                    @Override
                    public boolean test(Room room) throws Exception {
                        return room.getPrice() > 500;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Room>() {
                    @Override
                    public void accept(Room room) throws Exception {
                        displayUI(room);
                    }
                });

    }


    //#################使用rxJava写HelloWorld##################
    public void printlnRxjavaHelloWorldOne() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("Hello World");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public void printlnRxjavaHelloWorldTwo() {
        Observable.just("Hello World")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }


    public void printlnRxjavaHelloWorldThree() {
        Observable.just("Hello World")
                .subscribe(System.out::println);
    }


    //############rxjava操作符###########

    /**
     * just()是rxjava的创建操作符  用于创建一个Observable  Consumer是消费者用于接收单个值
     * 在
     * subscribe中onComplete是执行完onNext之后再执行的  onComplete是一个Action，它与
     * Consumer的区别如下：
     * 1  Action：无参数类型
     * 2 Consumer：单一参数类型
     * subscribe的重载方法  subscribe(onNext,onError,onComplete,onSubscribe)
     */
    public void justObservableJava() {
        Observable.just("Hello World")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * 使用rxjava中的subscribe
     * Observer接口  实现四个方法分别如下
     * <p>
     * onSubscribe开始订阅
     * <p>
     * onNext 数据成功返回
     * <p>
     * onError 获取数据失败
     * <p>
     * onComplete rxjava数据传输完成
     */
    public void justObservableSubscribe() {
        Observable.just("Hello World")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("subscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * 五种观察者模式的描述如下
     *
     * Observable 能够发射0或n个数据，并以成功或者错误事件终止
     *
     * Flowable 能够发射0或n个数据，并以成功或错误事件终止。支持背压，可以控制数据源发射的速度
     *
     * Single只发射单个数据或错误事件
     *
     * Completable从来不发射数据  只处理onComplete和onError事件。可以看成Rx的Runnable
     *
     * Maybe能够发射0或1个数据 要么成功，要么失败，有点类似于Optional
     */


    /**
     * do操作符
     * <p>
     * do操作符可以给Observable的生命周期的各个阶段加上一些列的回调监听，
     * 当Observable执行到这个阶段时，这些回调就会被触发，在Rxjava中包括了很多的doXXX操作符
     * <p>
     * <p>
     * <p>
     * 如下方法执行的结果如下
     * <p>
     * doOnSubscribe
     * doOnLifecycle:false
     * doOnNext:Hello
     * doOnEach:onNext
     * 收到消息：hello
     * doAfterNext：Hello
     * doOnComplete：
     * doOnEach：onComplete
     * doFinally:doAfterTerminate:
     * <p>
     * doOnSubscribe 一旦观察者订阅了Observable。它就会被调用
     * doOnLifecycle 可以在观察者订阅之后，设置是否取消订阅
     * doOnNext  它产生的Observable每发射一项数据就会调用它一次，它的Consumer接收发射的数据项。一般
     * 用于在subscribe之前对数据进行处理
     * doOnEach 它产生Observable每发射一项数据就会调用它一次，不仅包括onNext 还包括onError和哦那Complete
     * doAfterNext 在onNext之后执行，而doOnNext（）是在onNext之前执行
     * doOnComplete 当它产生的Observable在正常终止调用onComplete时会被调用
     * doFinally 在当它产生的Observable终止之后被调用，无论是正常终止还是异常终止doFinally优先于doAfterTerminate的调用
     * doAfterTerminate注册一个Action 当Observable调用onComplete或onError时触发
     */

    public void rxJavaDoOperate() {
        Observable.just("Hello")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnNext:" + s);
                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doAfterNext:" + s);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnComplete:");
                    }
                })
                //订阅之后回调的方法
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("doOnSubscribe:");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doAfterTerminate:");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doFinally:");
                    }
                })
                //Observable每发射一个数据就会触发这个回调，不仅包括onNext，还包括onError和onComplete
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(Notification<String> stringNotification) throws Exception {
                        System.out.println("doOnEach:" + (stringNotification.isOnNext() ? "onNext" : stringNotification.isOnComplete()));

                    }

                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("收到消息：" + s);
                    }
                });

    }


    /**
     * Hot Observable 和Cold Observable的区别
     *
     * 在Rxjava中有Hot 和 cold之分
     * Hot Observable无论有没有观察者进行订阅，事件始终都会发生。当Hot Observable有多个订阅者时 Hot Observable与订阅者们的关系是一对多的关系，可以与多个订阅者共享信息
     * Cold Observable是只有观察者订阅，才开始执行发射数据流的代码，并且Cold Observable和Observable只能一对一的关系，当有多个不同的订阅者时，消息时重新完整发送的
     * 也就是说对Cold Observable而言，有多个Observable的时候 他们各自的事件是独立的
     * 打个比方 Hot Observable想象成一个广播电台，所有对此刻收听到的听众都会听到同一首歌
     * 而Cold Observable是一张音乐CD 人们可以独立购买并听取他
     *
     * 2   Cold Observable
     * Observable的just create range fromxxx等操作符都能生成Cold Observable
     */


    /**
     * Rxjava中的创建操作符主要包括如下内容
     *
     * Just():将一个或多个对象转换成发送这个或这些对象的一个Observable
     * from（）：将一个Iterable 一个Future或者一个数组转换成一个Observable
     * create():使用一个函数从头创建一个Observable
     * defer():只有当订阅者订阅才创建Observable，为每个订阅创建一个新的Observable
     * range():创建一个发射指定范围的整数序列的Observable
     * interval():创建一个在给定的时间间隔发射整数序列的Observable
     * timer():创建一个在给定的延时之后发射单个数据的Observable
     * empty():创建一个什么都不做直接通知完成的Observable
     * error():创建一个什么都不做直接通知错误的Observable
     * never():创建一个不发射任何数据的Observable
     */

    /**
     * 1 create操作符
     * <p>
     * <p>
     * 使用一个函数从头开始创建一个Observable
     * <p>
     * 我们可以使用create操作符从头创建一个Observable，给这个操作符传递一个接收观察者作为参数的函数，编写这个函数让他的行为表现为一个Observable
     * 恰当地调用观察者的onNext onError和onComplete方法，一个形式正确的有限Observable必须尝试调用观察者的onComplete()一次或者它的onError()
     * 一次，而且此后不能再调用观察者的任何其他方法
     * Rxjava建议我们在传递给create方法的函数时，先检查一下观察者的isDisposed状态，以便在没有观察者模式的时候，让我们的Observable停止发射数据
     * 防止运行昂贵的运算
     */
    public void rxjavaCreateObservale() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                try {
                    if (!emitter.isDisposed()) {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Sequence complete:");
            }
        });
    }

    /**
     * just操作符
     * 创建一个发射指定的Observable
     * just将单个数据转换为发射这个单个数据的Observable
     * <p>
     * just类似于from 但是from会将数组或Iterable的数据取出然后逐个发射，而just只是简单地原样发射，将数组或Iterable当作单个数据
     * 他可以接收一到十个参数，返回一个按参数列表顺序发射这个数据的OBservable
     * <p>
     * 如果just传入一个null 则会抛出一个空指针异常
     */
    public void rxjavaJustObservable() {
        Observable.just("hello just")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });


        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete:");
                    }
                });
    }


    /**
     * from操作符
     * from 可以将其他种类的对象和数据类型转换为Observable。
     * 当我们使用Observable时，如果要处理的数据都可以转换成Observable，而不是需要混合使用Observable和其他类型的数据，会非常方便，这让我们在数据流
     * 的整个生命周期中，可以使用一组统一的操作符来管理它们
     * 例如：Iterable可以看成是同步的Observable；Future可以看成总是只发射单个数据的Observable。通过显式地将哪些数据转换为Observables，我们可以
     * 像使用Observable的方法
     * 在Rxjava中from操作符可以将Future Iterable和数组转换成Observable 对于Iterable和数组，产生的Observable会发射Iterable或数组的每一项数据
     * <p>
     * <p>
     * from方法有一个可以接收两个可选参数的版本，分别指定超时时长和时间单位。如果过了指定的时长，Future还没有返回一个值，那么这个Observable就会发射错误
     * 通知并终止
     */
    public void rxjavaFromObservable() {
        Observable.fromArray("hello", "from")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });


        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(i);
        }

        Observable.fromIterable(items)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete:");
                    }
                });

        //对于Future 他会发射Future.get()方法返回的单个数据
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Observable.fromFuture(future)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });


        Observable.fromFuture(future, 4000, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }


    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("模拟一些耗时的任务");
            Thread.sleep(5000);
            return "OK";
        }
    }


    /**
     * repeat操作符
     * repeat创建一个发射特定数据重复多次的Observable
     * repeat会重复地发射数据，某些实现允许我们重复发射某个数据序列，还有
     * 一些允许我们限制重复的次数
     * repeat不是创建一个Observable，而是重复发射原始Observable的数据序列
     * 这个序列或者是无限的，或者是通过repeat(n)指定的重复次数
     */

    public void rxjavaRepeatObservable() {
        Observable.just("hello repeat")
                .repeat(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                });
    }

    /**
     * repeatWhen操作符
     * repeatWhen不是缓存和重放原始Observable的数据序列，而是有条件地重新订阅和发射原来的Observable
     * 将原始Observable的终止通知(完成或错误)当作一个void数据传递给一个通知处理器，以此来决定是否要重新
     * 订阅和发射原来的Observable,这个通知处理器就像一个Observable操作符，接受一个发射void通知的Observable
     * 作为输入，返回一个发射void数据或者直接终止的Observable
     */
    public void rxjavaRepeatWhenObservable(){
        Observable.range(0,9)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.timer(10,TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * repeatUntil操作符
     *
     *repeatUntil表示直到某个条件就不再重复发射数据
     * 当booleansupplier的getAdBoolean()返回false时，表示重复发射上游的Observable；当getAsBoolean()为true时，表示中止重复发射上游的Observable
     */
    public void rxjavaRepeatUntilsObservable() throws InterruptedException {
        final long startTimeMillis = System.currentTimeMillis();
        Observable.interval(500,TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return System.currentTimeMillis() - startTimeMillis>5000;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        Thread.sleep(6000);
    }

    /**
     * defer操作符
     * 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个全新的Observable
     * defer操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。它对每个观察者都这样做，因此尽管每个订阅者都
     * 以为自己订阅的是同一个Observable，但事实上每个订阅者获取的是它们自己单独的数据序列
     * 在某种情况下 直到最后一分钟才生成Observable,以确保Observable包含最新的数据
     */

    public void rxjavaDeferObsevable(){
        Observable observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends  String> call() throws Exception {
                return Observable.just("hello defer");
            }
        });

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

    }

    /**
     * intertval操作符
     * 创建一个按固定时间隔发射整数序列的Obsevable
     *
     *intertval操作符返回一个Observable，它按固定的时间发射一个无限递增的整数序列
     * interval接受一个表示时间间隔的参数和一个表示时间单位的参数.interval默认在computation调度器上执行
     */
    public void rxjavaIntervalObservable() throws InterruptedException {
        Observable.interval(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        Thread.sleep(1000);
    }

    /**
     * timer操作符
     * 创建一个Observable,它在一个给定的延迟后发射一个特殊的值
     * timer操作符创建一个在给定的时间段之后返回一个特殊值的Observable
     * timer返回一个Observable，它在延迟一段给定的时间后发射一个简单的数字timer操作符默认在computation调度器上执行
     */

    public void rxjavaTimerObservable() throws InterruptedException {
        Observable.timer(2,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("hello timer");
                    }
                });

        Thread.sleep(1000);
    }

    /**
     * #####  Scheduler调度器 ##########
     *
     * 1 Single 作用  使用定长为1的线程池(new Scheduled Thread Pool(1),重复利用这个线程)
     *
     * 2 newThread 每次都启动一个新线程 并在新线程中的操作
     *
     * 3 computation 使用固定的线程池(Fixed Scheduler Pool),大小为CPU核数 适用于CPU密集型计算
     *
     * 4 io 使用I/O操作（读写文件,读写数据库,网络信息交互等）所使用的Scheduler。行为模式和newThread()差不多，区别在于io()
     * 内部实现是用一个无数量上限的线程池。可以重用空闲的线程，因此多数情况下,io()比newThread（）更有效率
     *
     * 5 trampoline 直接在当前线程运行，如果当前线程有其他任务正在执行， 则会先暂停其他任务
     *
     * 6 Scheduler 将java.util.concurrent.Executor转换成一个调度器实例，即可以自定义一个Executor来作为调度器
     */

    public void javaSchedulerObservable(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("hello");
                emitter.onNext("world");
            }
        }).observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    /**
     * rxjava的被观察者们在使用操作符时可以利用线程调度器---Scheduler来切换线程
     */

    public void rxjavaTranslateThread(){
        Observable.just("aaa","bbb")
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                }).observeOn(Schedulers.single())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    /**
     * 1  线程调度器Scheduler
     * Scheduler是一个静态工厂类，通过分析Scheduler的源码可以看到它有多种不同类型的Scheduler
     * Scheduler的各种工厂方法
     *
     * 1   computation()用于CPU密集型的计算任务，但并不适合I/O操作
     * @NonNull
     * public void Scheduler computation(){
     *     return RxJavaPlugins.onComputationScheduler(COMPUTATION)
     * }
     * 2  io()用于I/O密集型任务，支持异步阻塞I/O操作，这个调度器的线程池会根据需要增长。对于普通的计算任务，请使用Scheduler.computation()
     *
     * @NonNull
     * public void Scheduler io(){
     *     return RxJavaPlugins.onIoScheduler(IO);
     * }
     *
     * 3 trampoline()
     * 在RxJava2中与在RxJava1中的作用不同。在RxJava2中表示立即执行，如果当前线程有任务在执行，则会将其暂停，等插入进来的新任务执行完成之后，
     *再接着执行原先未执行的任务，在RxJava中，表示在当前线程中等待其他任务完成之后，再执行新的任务
     * @NonNull
     * public static Scheduler trampoline(){
     *     return TRAMPOLINE;
     * }
     *
     * 4 single()
     * Single()拥有一个线程单例，所有的任务都在一个线程中执行，当此线程中有任务执行时，他的任务将会按照先进先出的顺序依次执行
     * @NonNull
     * public void Scheduler single(){
     *     return RxJavaPlugins.onSingleScheduler(SINGLE);
     * }
     *
     * Scheduler是RxJava的线程任务调度器，Worker是线程任务的具体执行者。从Scheduler源码可以看到，Scheduler在scheduleDirect().
     * schedulePeriodicallDirect()方法中创建了Worker，然后会分别调用worker的schedule() schedulePeriodically来执行任务
     *
     *
     * public Disposable scheduleDirect(@NonNull Runnable run,long delay,@NonNull TimeUnit unit){
     *     final Worker w = createWorker();
     *     final Runnable decorateRun = RxJavaPlugins.onSchedule(run);
     *     DisposeTask task = new DisposeTask(decorateRun,w);
     *
     *     w.schedule(task,delay,unit);
     *
     *     return task;
     * }
     *
     *
     * SingleScheduler调度器
     * SingleScheduler是RxJava2新增的Scheduler。SingleScheduler中有一个属性叫作executor 它是使用AtomicReference包装的ScheduleExecutorService
     *
     *ComputationScheduler
     * ComputationScheduler 使用FixedSchedulerPool作为线程池，并且FixedSchedulerPool被AtomicReference包装了一下
     * 从ComputationScheduler的源码中可以看出，MAX_THREADS是CPU的数目。FixedSchedulerPool可以理解为拥有固定数量的线程池。数量为MAX_THREADS
     *
     *
     * IOScheduler
     * IoScheduler使用CachedWorkerPool作为线程池，并且CacheWorkerPool也被AtomicReference包装了一下
     *
     * CachedWorkerPool是基于RxThreadFactory这个ThreadFactory来创建的
     *
     * 在RxThreadFactory中，由prefix和incrementAndGet()来创建新线程的名称
     * IoScheduler创建的线程数不是固定的，可以通过IoScheduler的Size()来获得当前的线程数，一般情况下，ComputationScheduler的线程数等于CPU的数目
     *
     * public void size(){
     *     return pool.get().allWorker.size()
     * }
     *
     * ComputationScheduler和IoScheduler都是依赖线程池来维护线程的，区别就是IoScheduler线程池中的个数是无限的，由prefix和incrementAndGet()
     * 产生的递增来决定线程的名字，而ComputationScheduler中则是一个固定线程数量的线程池，数据为CPU的数目，并且不要把I/O操作放在computation中
     * 否则I/O操作的等待时间会浪费CPU
     * 同样 IoScheduler也会创建EventWorkerEventLoopWorker
     * 但这个EventLoopWorker是IoScheduler的内部类，与ComputationScheduler创建的EventLoopWorker，NewThreadWorker的构造函数使用的也是SchedulerPoolFactory
     *
     * 与SingleScheduler不同的是，SingleSchduler的executor是使用AtomicReference包装的ScheduledExecutorService,每次使用时，都会调用executor.get()\
     *
     * TrampolineScheduler
     * TrampolineScheduler会创建TrampolineWorker 在TrampolineWorker内部维护这一个PriorityBlockingQueue。任务进入该队列之前，会先用
     * TimedRunnable封装一下[
     */


    /**
     * 多次切换线程
     *subscribe指定线程行为
     *
     * observeOn指定线程的操作  也就是说是在那个线程进行的操作 ui线程还是其他的什么线程
     *
     * 执行的结果  第一次切换到Single线程
     * 第二次切换到I/O线程
     * 由于前面已经执行了subscribeOn 所以不做线程切换
     *
     * 最后切换到newThread
     */

    public void moreThanTranslateThread(){
        Observable.just("HELLO_WORLD")
                .subscribeOn(Schedulers.single())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        s = s.toLowerCase();
                        Log.i("mapl",s);
                        return s;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        s=s+"tony.";
                        Log.i("map2",s);
                        return s;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        s = s+"it is a test";
                        Log.i("map3",s);
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("subscribe",s);
                        System.out.println(s);
                    }
                });

    }


    /**
     * advanceTime 将调度器的时钟移动到某个特定时刻
     * 例如  时钟移动到10ms
     *
     * scheduler.advanceTimeTo(10,TimeUnit.MILLISECOND)
     *
     *
     * 执行结果 immediate
     *
     * virtual time ：1
     * 20
     *
     * virtual time:2
     * 40
     *
     * virtual time:40
     *
     *
     * 可以看到使用advanceTimeTo之后，移动不同的时间点会打印不同的内容，
     * 当然，advanceTimeTo()也可以传负数，表示回到过去的时间点，但是一般不推荐这种做法
     */
    public void rxjavaAdvanceTimeObservable(){
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        },20,TimeUnit.SECONDS);

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("40s");
            }
        },40,TimeUnit.SECONDS);

        scheduler.advanceTimeTo(1,TimeUnit.MILLISECONDS);
        System.out.println("virtual time:"+scheduler.now(TimeUnit.MILLISECONDS));

        scheduler.advanceTimeTo(20,TimeUnit.SECONDS);
        System.out.println("virtual time:"+scheduler.now(TimeUnit.MILLISECONDS));

    }

    /**
     * advanceTimeBy
     *
     *将调度程序的时钟按指定的时间向前移动
     * 例如  时钟移动了10ms
     *
     * scheduler.advanceTimeBy(10,TimeUnit.MILLISECONDS)
     * 再次调用刚才的方法 时钟会移动10ms。此时，时钟移动到20ms，这是一个累加的过程
     * scheduler.advanceTimeBy(10,TimeUnit.MILLISECONDS)
     *下面的例子使用了timer操作符，timer是按照指定时间延迟发送的操作符，timer（）并不会按周期地执行
     * 执行结果
     * atomicLong`s value=0,virtual time:0
     * atomicLong`s value=0,virtual time:1
     * atomicLong`s value=1,virtual time:2
     *
     *  这个结果符合预期，最初atomicLong为0，时钟移动到1s时它的值仍然为0；
     *  时钟再移动1s，即相当于时钟移动到2s，所以它的值为1
     *
     */

    public void rxjavaAdvanceTimeByObservable(){
        TestScheduler scheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2,TimeUnit.SECONDS,scheduler)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        atomicLong.incrementAndGet();
                    }
                });

        System.out.println("atomicLong`s value="+atomicLong.get()+",virtual time:"+scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(1,TimeUnit.SECONDS);

        System.out.println("atomicLong`s value="+atomicLong.get()+",virtual time:"+scheduler.now(TimeUnit.SECONDS));
    }
    /**
     * surely，advanceTimeBy() 也可以传负数，表示回到过去
     *
     * 执行结果
     * atomicLong`s value=0,virtual time:0
     * atomicLong`s value=0,virtual time:1
     * atomicLong`s value=0,virtual time:0
     * atomicLong`s value=1,virtual time:2
     */
    public void rxjavaAdvanceTimeByObservableTwo(){
        TestScheduler scheduler = new TestScheduler();

        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2,TimeUnit.SECONDS,scheduler)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        atomicLong.incrementAndGet();
                    }
                });

        System.out.println("atomicLong`s value="+atomicLong.get()+",virtual time:"+scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(1,TimeUnit.SECONDS);
        System.out.println("atomicLong`s value="+atomicLong.get()+",virtual time:"+scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(-1,TimeUnit.SECONDS);
        System.out.println("atomicLong`s value="+ atomicLong.get()+",virtual time:"+scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(2,TimeUnit.SECONDS);
        System.out.println("atomicLong`s value="+atomicLong.get()+",virtual time"+scheduler.now(TimeUnit.SECONDS));
    }

    /**
     * triggerActions
     * triggerActions不会修改时间，它执行计划中的但是未启动的任务，已经执行过的任务不会再启动
     */

    public void rxjavaTriggerActionObservable(){
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        },20,TimeUnit.SECONDS);

        scheduler.triggerActions();
        System.out.println("virtual time:"+scheduler.now(TimeUnit.SECONDS));
        scheduler.advanceTimeBy(20,TimeUnit.SECONDS);

    }


    //######################### 变换操作符和过滤操作符 #################

    /**
     * RxJva的操作符主要包括一下几种
     *
     *
     * 1 map():对序列的每一项都用一个函数来变换 Observable 发射的数据序列
     *
     *
     * 2 flatMap()/concatMap()和flatMapIterable():将Observable发射的数据集合变换为Observables集合，
     * 然后将这些Observable发射的数据平坦化地放近一个单独的Observable中
     *
     * 3 scan():对Observable发射的每一项数据应用一个函数,然后按顺序依次发射每一个值
     *
     * 4 groupBy(): 将Observable拆分为Observable集合，将原始Observable发射的数据按key分组，每一个Observable发射过一组不同的数据
     *
     * 5 buffer():定期从Observable收集数据到一个集合，然后把这些数据集合打包发射,而不是一次发射一个
     *
     * 6 window():定期将来自Observable的数据拆分成一些Observable窗口，然后发射这些窗口，而不是每次发射一项
     *
     * 7 cast():在发射之前强制将Observable发射的所有数据转换为指定类型
     *
     * RxJava的过滤操作符主要包括一下几种
     *
     * 1  filter():过滤数据
     *
     * 2  takeLast():只发射最后的N项数据
     *
     * 3  last():只发射最后一项数据
     *
     * 4 lastOrDefault():只发射最后一项数据，如果Observable为空，就发射默认值
     *
     * 5 takeLastBuffer():将最后的N项数据当作单个数据发射
     *
     * 6 skip():跳过开始的N项数据
     *
     * 7 skipLast():跳过最后的N项数据
     *
     * 8 take():只发射开始的N项数据
     *
     * 9 first():and takeFirst():只发射第一项数据 ，或者满足某种条件的第一项数据
     *
     * 10 firstOrDefault():只发射第一项数据，如果Observable为空，就发射默认值
     *
     * 11 elementAt():发射第N项数据
     *
     * 12 elementAtOrDefault():发射第N项数据，如果Observable数据少于N项,就发射默认值。
     *
     * 13 sample() or throttleLast():定期发射Observable最近的数据
     *
     * 14 throttleFirst():定期发射Observable发射的第一项数据
     *
     * 15 throttleWithTimeout() or debounce():只是当Observable在指定的看时间段后还没有发射数据时，才发射一个数据
     *
     * 16 timeout() : 如果在一个指定的时间断后还没发射数据，就发射一个异常
     *
     * 17 distinct(): 过滤掉重复的数据
     *
     * 18 distinctUntilChanged():过滤掉连续重复的数据
     *
     * 19 ofType():只发射指定类型的数据
     *
     * 20 ignoreElements():丢弃所有的正常数据。只发射错误或完成通知
     */

    /**
     * 1 map操作符
     *
     * 对Observable发射的每一项数据应用一个函数，执行变换操作
     *
     * map操作符对原始Observable发射的每一项数据应用一个你选择的函数，然后返回一个发射这些结果的Observable
     *
     * RxJava将这些操作符实现为map函数 这些操作符默认不再任何特定的调度器上执行
     */


    public void rxjavaMapObservable(){
        Observable.just("HELLO")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toLowerCase();
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s+"world";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    /**
     * flatmap操作符
     *
     *  flatmap将一个发射数据的Observable变换为多个Observables,然后将他们发射的数据合并放进一个单独的Observable
     *  flatmap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable
     *  然后flatmap合并这些Observable发射的数据，最后将合并后的结果当作它自己的数据序列发射
     *
     *  flatMap对这些Observable发射的数据做的是合并(merge)操作，因此它们可能是交错的，还有一个操作符不会让变换后的Observable发射的
     *  数据交错，它严格按照顺序发射这些数据，这个操作符就是concatMap
     *
     *  map和flatMap是RxJava中使用率非常高的操作符，熟练掌握它们是非常有必要，在笔者看来它们的重要性仅次于第三章的创建操作符
     */
    public void rxjavaFlatmapObservable(){
        User user = new User();
        user.userName = "tony";
        user.addresses = new ArrayList<>();
        User.Address address1 = new User.Address();
        address1.street = "ren ming road";
        address1.city = "Su Zhou";
        user.addresses.add(address1);

        User.Address address2 = new User.Address();
        address2.street = "dong wu bei road";
        address2.city = "Su Zhou";
        user.addresses.add(address2);
        //使用map
        Observable.create(new ObservableOnSubscribe<User>() {

            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                emitter.onNext(user);
            }
        }).map(new Function<User, List<User.Address>>() {
            @Override
            public List<User.Address> apply(User user) {
                return user.addresses;
            }
        }).subscribe(new Consumer<List<User.Address>>() {
            @Override
            public void accept(List<User.Address> addresses) throws Exception {
                for (User.Address address:addresses){
                    System.out.println(address);
                }
            }
        });


        //使用flatmap
        Observable.just(user) .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public Observable<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.addresses);
                    }
                })
                .subscribe(new Consumer<User.Address>() {
                    @Override
                    public void accept(User.Address address) throws Exception {
                        System.out.println(address);
                    }
                });

    }

    /**
     *  groupBy操作符
     *
     *  groupBy操作符将一个Observable拆分为一些Observable集合，它们中的每一个都发射原始Observable的一个子序列
     *  哪个数据项由哪一个Observable发射是由一个函数判定的，这个函数给每一项指定一个Key，Key相同的数据会被同一个Observable发射
     *  最终返回的是Observable的一个特殊子类GroupedObservable。它是一个抽象类。getKey()方法是GroupedObservable的方法,这个
     *  key用于将数据分组到指定的Observable
     *
     *  使用GroupedObservable使用getKey()方法，从而能够选出奇数组的GroupedObservable，最后打印出该GroupedObservable下的全部成员
     *
     */

    public void rxjavaGroupByObservable(){
        Observable.range(1,8)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return (integer % 2 ==0)?"偶数组":"奇数组";
                    }
                })
                .subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                        System.out.println("group name:"+stringIntegerGroupedObservable.getKey());
                        if (stringIntegerGroupedObservable.getKey().equals("奇数组")){
                            stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    System.out.println(integer);
                                }
                            });
                        }
                    }
                });

    }


    /**
     * buffer操作符
     *
     * buffer会定期收集Observable数据并放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值
     * buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，由变换发生的Observable发射这些数据的缓存集合
     * 在RxJava中有许多buffer的重载方法，例如比较常用的buffer(count,skip)
     *
     * buffer(count,skip)从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，就用count项数据填充缓存；开始的一项和
     * 后续的count-1项，也可能会有间隙 取决于count和skip的值
     *
     * 如果原来的Observable发射了一个onError通知，那么buffer会立即传递这个通知，而不是首先发射缓存的数据，即使在这之前缓存中包括了原始Observable
     * 发射的数据
     *
     *
     *
     * 执行结果
     * onNext:[1,2,3,4,5]
     * onNext:[2,3,4,5,6]
     * onNext:[3,4,5,6,7]
     * onNext:[4,5,6,7,8]
     * onNext:[5,6,7,8,9]
     * onNext:[6,7,8,9,10]
     * onNext:[7,8,9,10]
     * onNext:[8,9,10]
     * onNext:[9,10]
     * onNext:[10]
     */

    public void rxjavaBufferObservable(){

        Observable.range(1,10)
                .buffer(2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError:");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });

        Observable.range(1,10)
                .buffer(5,1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("onNext:" + integers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError:");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * window操作符
     *
     * 定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射一项数据
     *
     * window发射的不是原始Observable的数据包，而是Observables。这些Observable中的每一个都发射原始Observable数据的一个子集
     * 最后发射一个onComplete通知
     */

    public void rxjavaWindowObservable(){
        Observable.range(1,10)
                .window(2)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        System.out.println("onNext:");
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println("accept:" + integer);
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError:");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }


    /**
     * first操作符
     * 只发射第一项(或者满足某个条件的第一项)数据
     * 如果只对Observable发射的第一项数据，或者满足某个条件的第一项数据感兴趣，那么就可以使用first操作符
     * 在RxJava2.x中，使用first()需要一个默认的Item，对于Observable而言，使用了first()会返回Single类型
     *
     * 如果Observable不发射任何数据,那么first操作符的默认值就起了作用
     */
    public void rxjavaFirstObservable(){
        Observable.just(1,2,3,4)
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:"+throwable.getMessage());
                    }
                });
        //执行结果Next：1
        Observable.<Integer>empty()
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError:"+throwable.getMessage());
                    }
                });
    }

    /**
     * last操作符
     * 只发射最后一项数据
     *
     *如果只对Observable发射的最后一项数据，或者满足某个条件的最后一项数据感兴趣，那么就可以使用last操作符
     *
     * last操作符跟first类似，需要一个默认的Item，也是返回Single类型
     */

    public void rxjavaLastObservable(){
        Observable.just(1,2,3)
                .last(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:"+integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:"+throwable.getMessage());
                    }
                });
    }

    /**
     * take操作符
     * 只发射前面的n项数据
     * 使用take操作符可以只修改Observable的行为，返回前面的n项数据，发射完成通知，忽略剩余的数据
     *执行的结果
     * 1 2 3 Sequence complete
     *
     * 如果对一个Observable使用take(n)操作符，而那个Observable发射的数据少于n项，那么take操作符生成的Observable就不会抛出异常或者
     * 发射onError通知，而是仍然会发射哪些数据
     */

    public void rxjavaTakeObservable(){
        Observable.just(1,2,3,4,5)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next:" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete");
                    }
                });


        /**
         * take有一个重载方法能够接受一个时长而不是数量参数。他会丢掉发射Observable开始的那段时间发射的数据
         * 时长和时间单位通过的参数指定
         */

        Observable.intervalRange(1,10,1,TimeUnit.SECONDS)
                .take(3,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Next:" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("onError:"+throwable.getMessage());
                    }
                });
    }












}
