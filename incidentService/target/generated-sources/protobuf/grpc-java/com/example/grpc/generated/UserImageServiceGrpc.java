package com.example.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class UserImageServiceGrpc {

  private UserImageServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "userimage.UserImageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.generated.GetImagesByIdRequest,
      com.example.grpc.generated.GetImagesResponse> getGetImagesByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getImagesById",
      requestType = com.example.grpc.generated.GetImagesByIdRequest.class,
      responseType = com.example.grpc.generated.GetImagesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.generated.GetImagesByIdRequest,
      com.example.grpc.generated.GetImagesResponse> getGetImagesByIdMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.generated.GetImagesByIdRequest, com.example.grpc.generated.GetImagesResponse> getGetImagesByIdMethod;
    if ((getGetImagesByIdMethod = UserImageServiceGrpc.getGetImagesByIdMethod) == null) {
      synchronized (UserImageServiceGrpc.class) {
        if ((getGetImagesByIdMethod = UserImageServiceGrpc.getGetImagesByIdMethod) == null) {
          UserImageServiceGrpc.getGetImagesByIdMethod = getGetImagesByIdMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.generated.GetImagesByIdRequest, com.example.grpc.generated.GetImagesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getImagesById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetImagesByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetImagesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserImageServiceMethodDescriptorSupplier("getImagesById"))
              .build();
        }
      }
    }
    return getGetImagesByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByAnalystIdRequest,
      com.example.grpc.generated.GetUserResponse> getGetUserByAnalystIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserByAnalystId",
      requestType = com.example.grpc.generated.GetUserByAnalystIdRequest.class,
      responseType = com.example.grpc.generated.GetUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByAnalystIdRequest,
      com.example.grpc.generated.GetUserResponse> getGetUserByAnalystIdMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByAnalystIdRequest, com.example.grpc.generated.GetUserResponse> getGetUserByAnalystIdMethod;
    if ((getGetUserByAnalystIdMethod = UserImageServiceGrpc.getGetUserByAnalystIdMethod) == null) {
      synchronized (UserImageServiceGrpc.class) {
        if ((getGetUserByAnalystIdMethod = UserImageServiceGrpc.getGetUserByAnalystIdMethod) == null) {
          UserImageServiceGrpc.getGetUserByAnalystIdMethod = getGetUserByAnalystIdMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.generated.GetUserByAnalystIdRequest, com.example.grpc.generated.GetUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUserByAnalystId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetUserByAnalystIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserImageServiceMethodDescriptorSupplier("getUserByAnalystId"))
              .build();
        }
      }
    }
    return getGetUserByAnalystIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByInitiatorIdRequest,
      com.example.grpc.generated.GetUserResponse> getGetUserByInitiatorIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserByInitiatorId",
      requestType = com.example.grpc.generated.GetUserByInitiatorIdRequest.class,
      responseType = com.example.grpc.generated.GetUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByInitiatorIdRequest,
      com.example.grpc.generated.GetUserResponse> getGetUserByInitiatorIdMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.generated.GetUserByInitiatorIdRequest, com.example.grpc.generated.GetUserResponse> getGetUserByInitiatorIdMethod;
    if ((getGetUserByInitiatorIdMethod = UserImageServiceGrpc.getGetUserByInitiatorIdMethod) == null) {
      synchronized (UserImageServiceGrpc.class) {
        if ((getGetUserByInitiatorIdMethod = UserImageServiceGrpc.getGetUserByInitiatorIdMethod) == null) {
          UserImageServiceGrpc.getGetUserByInitiatorIdMethod = getGetUserByInitiatorIdMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.generated.GetUserByInitiatorIdRequest, com.example.grpc.generated.GetUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUserByInitiatorId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetUserByInitiatorIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.generated.GetUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserImageServiceMethodDescriptorSupplier("getUserByInitiatorId"))
              .build();
        }
      }
    }
    return getGetUserByInitiatorIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserImageServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserImageServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserImageServiceStub>() {
        @java.lang.Override
        public UserImageServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserImageServiceStub(channel, callOptions);
        }
      };
    return UserImageServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static UserImageServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserImageServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserImageServiceBlockingV2Stub>() {
        @java.lang.Override
        public UserImageServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserImageServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return UserImageServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserImageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserImageServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserImageServiceBlockingStub>() {
        @java.lang.Override
        public UserImageServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserImageServiceBlockingStub(channel, callOptions);
        }
      };
    return UserImageServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserImageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserImageServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserImageServiceFutureStub>() {
        @java.lang.Override
        public UserImageServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserImageServiceFutureStub(channel, callOptions);
        }
      };
    return UserImageServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getImagesById(com.example.grpc.generated.GetImagesByIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetImagesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetImagesByIdMethod(), responseObserver);
    }

    /**
     */
    default void getUserByAnalystId(com.example.grpc.generated.GetUserByAnalystIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserByAnalystIdMethod(), responseObserver);
    }

    /**
     */
    default void getUserByInitiatorId(com.example.grpc.generated.GetUserByInitiatorIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserByInitiatorIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UserImageService.
   */
  public static abstract class UserImageServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UserImageServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UserImageService.
   */
  public static final class UserImageServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UserImageServiceStub> {
    private UserImageServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserImageServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserImageServiceStub(channel, callOptions);
    }

    /**
     */
    public void getImagesById(com.example.grpc.generated.GetImagesByIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetImagesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetImagesByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserByAnalystId(com.example.grpc.generated.GetUserByAnalystIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserByAnalystIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserByInitiatorId(com.example.grpc.generated.GetUserByInitiatorIdRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserByInitiatorIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UserImageService.
   */
  public static final class UserImageServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<UserImageServiceBlockingV2Stub> {
    private UserImageServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserImageServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserImageServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.generated.GetImagesResponse getImagesById(com.example.grpc.generated.GetImagesByIdRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetImagesByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.generated.GetUserResponse getUserByAnalystId(com.example.grpc.generated.GetUserByAnalystIdRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetUserByAnalystIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.generated.GetUserResponse getUserByInitiatorId(com.example.grpc.generated.GetUserByInitiatorIdRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetUserByInitiatorIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service UserImageService.
   */
  public static final class UserImageServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UserImageServiceBlockingStub> {
    private UserImageServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserImageServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserImageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.generated.GetImagesResponse getImagesById(com.example.grpc.generated.GetImagesByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetImagesByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.generated.GetUserResponse getUserByAnalystId(com.example.grpc.generated.GetUserByAnalystIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserByAnalystIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.generated.GetUserResponse getUserByInitiatorId(com.example.grpc.generated.GetUserByInitiatorIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserByInitiatorIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UserImageService.
   */
  public static final class UserImageServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UserImageServiceFutureStub> {
    private UserImageServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserImageServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserImageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.generated.GetImagesResponse> getImagesById(
        com.example.grpc.generated.GetImagesByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetImagesByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.generated.GetUserResponse> getUserByAnalystId(
        com.example.grpc.generated.GetUserByAnalystIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserByAnalystIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.generated.GetUserResponse> getUserByInitiatorId(
        com.example.grpc.generated.GetUserByInitiatorIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserByInitiatorIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_IMAGES_BY_ID = 0;
  private static final int METHODID_GET_USER_BY_ANALYST_ID = 1;
  private static final int METHODID_GET_USER_BY_INITIATOR_ID = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_IMAGES_BY_ID:
          serviceImpl.getImagesById((com.example.grpc.generated.GetImagesByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.generated.GetImagesResponse>) responseObserver);
          break;
        case METHODID_GET_USER_BY_ANALYST_ID:
          serviceImpl.getUserByAnalystId((com.example.grpc.generated.GetUserByAnalystIdRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse>) responseObserver);
          break;
        case METHODID_GET_USER_BY_INITIATOR_ID:
          serviceImpl.getUserByInitiatorId((com.example.grpc.generated.GetUserByInitiatorIdRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.generated.GetUserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetImagesByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.generated.GetImagesByIdRequest,
              com.example.grpc.generated.GetImagesResponse>(
                service, METHODID_GET_IMAGES_BY_ID)))
        .addMethod(
          getGetUserByAnalystIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.generated.GetUserByAnalystIdRequest,
              com.example.grpc.generated.GetUserResponse>(
                service, METHODID_GET_USER_BY_ANALYST_ID)))
        .addMethod(
          getGetUserByInitiatorIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.generated.GetUserByInitiatorIdRequest,
              com.example.grpc.generated.GetUserResponse>(
                service, METHODID_GET_USER_BY_INITIATOR_ID)))
        .build();
  }

  private static abstract class UserImageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserImageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.generated.UserImageServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserImageService");
    }
  }

  private static final class UserImageServiceFileDescriptorSupplier
      extends UserImageServiceBaseDescriptorSupplier {
    UserImageServiceFileDescriptorSupplier() {}
  }

  private static final class UserImageServiceMethodDescriptorSupplier
      extends UserImageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UserImageServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserImageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserImageServiceFileDescriptorSupplier())
              .addMethod(getGetImagesByIdMethod())
              .addMethod(getGetUserByAnalystIdMethod())
              .addMethod(getGetUserByInitiatorIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
