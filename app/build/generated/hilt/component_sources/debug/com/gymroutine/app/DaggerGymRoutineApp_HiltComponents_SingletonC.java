package com.gymroutine.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.gymroutine.app.data.local.dao.ExerciseDao;
import com.gymroutine.app.data.local.dao.ExerciseLogDao;
import com.gymroutine.app.data.local.dao.RoutineDao;
import com.gymroutine.app.data.local.dao.WorkoutDayDao;
import com.gymroutine.app.data.local.dao.WorkoutSessionDao;
import com.gymroutine.app.data.local.database.GymRoutineDatabase;
import com.gymroutine.app.data.repository.ExerciseLogRepositoryImpl;
import com.gymroutine.app.data.repository.ExerciseRepositoryImpl;
import com.gymroutine.app.data.repository.RoutineRepositoryImpl;
import com.gymroutine.app.data.repository.WorkoutDayRepositoryImpl;
import com.gymroutine.app.data.repository.WorkoutSessionRepositoryImpl;
import com.gymroutine.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.gymroutine.app.di.DatabaseModule_ProvideExerciseDaoFactory;
import com.gymroutine.app.di.DatabaseModule_ProvideExerciseLogDaoFactory;
import com.gymroutine.app.di.DatabaseModule_ProvideRoutineDaoFactory;
import com.gymroutine.app.di.DatabaseModule_ProvideWorkoutDayDaoFactory;
import com.gymroutine.app.di.DatabaseModule_ProvideWorkoutSessionDaoFactory;
import com.gymroutine.app.domain.repository.ExerciseLogRepository;
import com.gymroutine.app.domain.repository.ExerciseRepository;
import com.gymroutine.app.domain.repository.RoutineRepository;
import com.gymroutine.app.domain.repository.WorkoutDayRepository;
import com.gymroutine.app.domain.repository.WorkoutSessionRepository;
import com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel;
import com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel;
import com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel;
import com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.HomeViewModel;
import com.gymroutine.app.presentation.viewmodel.HomeViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.ProgressViewModel;
import com.gymroutine.app.presentation.viewmodel.ProgressViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.ProgressViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.ProgressViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.RoutineViewModel;
import com.gymroutine.app.presentation.viewmodel.RoutineViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.RoutineViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.RoutineViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.SearchViewModel;
import com.gymroutine.app.presentation.viewmodel.SearchViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.SettingsViewModel;
import com.gymroutine.app.presentation.viewmodel.SettingsViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel;
import com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel;
import com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel;
import com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel_HiltModules;
import com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DaggerGymRoutineApp_HiltComponents_SingletonC {
  private DaggerGymRoutineApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public GymRoutineApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements GymRoutineApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements GymRoutineApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements GymRoutineApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements GymRoutineApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements GymRoutineApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements GymRoutineApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements GymRoutineApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public GymRoutineApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends GymRoutineApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends GymRoutineApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends GymRoutineApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends GymRoutineApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    Map keySetMapOfClassOfAndBooleanBuilder() {
      MapBuilder mapBuilder = MapBuilder.<String, Boolean>newMapBuilder(11);
      mapBuilder.put(ActiveWorkoutViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ActiveWorkoutViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(CreateRoutineViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CreateRoutineViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ExerciseHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ExerciseHistoryViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(ProgressViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProgressViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(RoutineViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, RoutineViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SearchViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(StartWorkoutViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, StartWorkoutViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(WorkoutCompleteViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, WorkoutCompleteViewModel_HiltModules.KeyModule.provide());
      mapBuilder.put(WorkoutDayViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, WorkoutDayViewModel_HiltModules.KeyModule.provide());
      return mapBuilder.build();
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(keySetMapOfClassOfAndBooleanBuilder());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends GymRoutineApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<ActiveWorkoutViewModel> activeWorkoutViewModelProvider;

    Provider<CreateRoutineViewModel> createRoutineViewModelProvider;

    Provider<ExerciseHistoryViewModel> exerciseHistoryViewModelProvider;

    Provider<HomeViewModel> homeViewModelProvider;

    Provider<ProgressViewModel> progressViewModelProvider;

    Provider<RoutineViewModel> routineViewModelProvider;

    Provider<SearchViewModel> searchViewModelProvider;

    Provider<SettingsViewModel> settingsViewModelProvider;

    Provider<StartWorkoutViewModel> startWorkoutViewModelProvider;

    Provider<WorkoutCompleteViewModel> workoutCompleteViewModelProvider;

    Provider<WorkoutDayViewModel> workoutDayViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    Map hiltViewModelMapMapOfClassOfAndProviderOfViewModelBuilder() {
      MapBuilder mapBuilder = MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(11);
      mapBuilder.put(ActiveWorkoutViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (activeWorkoutViewModelProvider)));
      mapBuilder.put(CreateRoutineViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (createRoutineViewModelProvider)));
      mapBuilder.put(ExerciseHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (exerciseHistoryViewModelProvider)));
      mapBuilder.put(HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (homeViewModelProvider)));
      mapBuilder.put(ProgressViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (progressViewModelProvider)));
      mapBuilder.put(RoutineViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (routineViewModelProvider)));
      mapBuilder.put(SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (searchViewModelProvider)));
      mapBuilder.put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (settingsViewModelProvider)));
      mapBuilder.put(StartWorkoutViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (startWorkoutViewModelProvider)));
      mapBuilder.put(WorkoutCompleteViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (workoutCompleteViewModelProvider)));
      mapBuilder.put(WorkoutDayViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (workoutDayViewModelProvider)));
      return mapBuilder.build();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.activeWorkoutViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.createRoutineViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.exerciseHistoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.progressViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.routineViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.startWorkoutViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.workoutCompleteViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.workoutDayViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(hiltViewModelMapMapOfClassOfAndProviderOfViewModelBuilder());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.gymroutine.app.presentation.viewmodel.ActiveWorkoutViewModel
          return (T) new ActiveWorkoutViewModel(singletonCImpl.bindWorkoutDayRepositoryProvider.get(), singletonCImpl.bindExerciseRepositoryProvider.get(), singletonCImpl.bindWorkoutSessionRepositoryProvider.get(), singletonCImpl.bindExerciseLogRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 1: // com.gymroutine.app.presentation.viewmodel.CreateRoutineViewModel
          return (T) new CreateRoutineViewModel(singletonCImpl.bindRoutineRepositoryProvider.get());

          case 2: // com.gymroutine.app.presentation.viewmodel.ExerciseHistoryViewModel
          return (T) new ExerciseHistoryViewModel(singletonCImpl.bindExerciseRepositoryProvider.get(), singletonCImpl.bindExerciseLogRepositoryProvider.get(), singletonCImpl.bindWorkoutSessionRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 3: // com.gymroutine.app.presentation.viewmodel.HomeViewModel
          return (T) new HomeViewModel(singletonCImpl.bindRoutineRepositoryProvider.get(), singletonCImpl.bindWorkoutSessionRepositoryProvider.get());

          case 4: // com.gymroutine.app.presentation.viewmodel.ProgressViewModel
          return (T) new ProgressViewModel(singletonCImpl.bindWorkoutSessionRepositoryProvider.get(), singletonCImpl.bindRoutineRepositoryProvider.get(), singletonCImpl.bindExerciseLogRepositoryProvider.get());

          case 5: // com.gymroutine.app.presentation.viewmodel.RoutineViewModel
          return (T) new RoutineViewModel(singletonCImpl.bindRoutineRepositoryProvider.get(), singletonCImpl.bindWorkoutDayRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 6: // com.gymroutine.app.presentation.viewmodel.SearchViewModel
          return (T) new SearchViewModel(singletonCImpl.bindRoutineRepositoryProvider.get(), singletonCImpl.bindWorkoutDayRepositoryProvider.get(), singletonCImpl.bindExerciseRepositoryProvider.get());

          case 7: // com.gymroutine.app.presentation.viewmodel.SettingsViewModel
          return (T) new SettingsViewModel();

          case 8: // com.gymroutine.app.presentation.viewmodel.StartWorkoutViewModel
          return (T) new StartWorkoutViewModel(singletonCImpl.bindRoutineRepositoryProvider.get(), singletonCImpl.bindWorkoutDayRepositoryProvider.get(), singletonCImpl.bindWorkoutSessionRepositoryProvider.get());

          case 9: // com.gymroutine.app.presentation.viewmodel.WorkoutCompleteViewModel
          return (T) new WorkoutCompleteViewModel(singletonCImpl.bindWorkoutSessionRepositoryProvider.get(), singletonCImpl.bindWorkoutDayRepositoryProvider.get(), singletonCImpl.bindExerciseLogRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 10: // com.gymroutine.app.presentation.viewmodel.WorkoutDayViewModel
          return (T) new WorkoutDayViewModel(singletonCImpl.bindWorkoutDayRepositoryProvider.get(), singletonCImpl.bindExerciseRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends GymRoutineApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends GymRoutineApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends GymRoutineApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<GymRoutineDatabase> provideDatabaseProvider;

    Provider<WorkoutDayRepositoryImpl> workoutDayRepositoryImplProvider;

    Provider<WorkoutDayRepository> bindWorkoutDayRepositoryProvider;

    Provider<ExerciseRepositoryImpl> exerciseRepositoryImplProvider;

    Provider<ExerciseRepository> bindExerciseRepositoryProvider;

    Provider<WorkoutSessionRepositoryImpl> workoutSessionRepositoryImplProvider;

    Provider<WorkoutSessionRepository> bindWorkoutSessionRepositoryProvider;

    Provider<ExerciseLogRepositoryImpl> exerciseLogRepositoryImplProvider;

    Provider<ExerciseLogRepository> bindExerciseLogRepositoryProvider;

    Provider<RoutineRepositoryImpl> routineRepositoryImplProvider;

    Provider<RoutineRepository> bindRoutineRepositoryProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    WorkoutDayDao workoutDayDao() {
      return DatabaseModule_ProvideWorkoutDayDaoFactory.provideWorkoutDayDao(provideDatabaseProvider.get());
    }

    ExerciseDao exerciseDao() {
      return DatabaseModule_ProvideExerciseDaoFactory.provideExerciseDao(provideDatabaseProvider.get());
    }

    WorkoutSessionDao workoutSessionDao() {
      return DatabaseModule_ProvideWorkoutSessionDaoFactory.provideWorkoutSessionDao(provideDatabaseProvider.get());
    }

    ExerciseLogDao exerciseLogDao() {
      return DatabaseModule_ProvideExerciseLogDaoFactory.provideExerciseLogDao(provideDatabaseProvider.get());
    }

    RoutineDao routineDao() {
      return DatabaseModule_ProvideRoutineDaoFactory.provideRoutineDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<GymRoutineDatabase>(singletonCImpl, 1));
      this.workoutDayRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindWorkoutDayRepositoryProvider = DoubleCheck.provider((Provider) (workoutDayRepositoryImplProvider));
      this.exerciseRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 2);
      this.bindExerciseRepositoryProvider = DoubleCheck.provider((Provider) (exerciseRepositoryImplProvider));
      this.workoutSessionRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 3);
      this.bindWorkoutSessionRepositoryProvider = DoubleCheck.provider((Provider) (workoutSessionRepositoryImplProvider));
      this.exerciseLogRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 4);
      this.bindExerciseLogRepositoryProvider = DoubleCheck.provider((Provider) (exerciseLogRepositoryImplProvider));
      this.routineRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 5);
      this.bindRoutineRepositoryProvider = DoubleCheck.provider((Provider) (routineRepositoryImplProvider));
    }

    @Override
    public void injectGymRoutineApp(GymRoutineApp gymRoutineApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.gymroutine.app.data.repository.WorkoutDayRepositoryImpl
          return (T) new WorkoutDayRepositoryImpl(singletonCImpl.workoutDayDao(), singletonCImpl.exerciseDao());

          case 1: // com.gymroutine.app.data.local.database.GymRoutineDatabase
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.gymroutine.app.data.repository.ExerciseRepositoryImpl
          return (T) new ExerciseRepositoryImpl(singletonCImpl.exerciseDao());

          case 3: // com.gymroutine.app.data.repository.WorkoutSessionRepositoryImpl
          return (T) new WorkoutSessionRepositoryImpl(singletonCImpl.workoutSessionDao());

          case 4: // com.gymroutine.app.data.repository.ExerciseLogRepositoryImpl
          return (T) new ExerciseLogRepositoryImpl(singletonCImpl.exerciseLogDao());

          case 5: // com.gymroutine.app.data.repository.RoutineRepositoryImpl
          return (T) new RoutineRepositoryImpl(singletonCImpl.routineDao(), singletonCImpl.workoutDayDao(), singletonCImpl.exerciseDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
